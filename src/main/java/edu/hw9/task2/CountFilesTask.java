package edu.hw9.task2;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import lombok.SneakyThrows;

public class CountFilesTask extends RecursiveTask<List<String>> {

    private final Path directory;
    private final int minFiles;

    public CountFilesTask(Path directory, int minFiles) {
        this.directory = directory;
        this.minFiles = minFiles;
    }

    @Override
    @SneakyThrows
    protected List<String> compute() {
        List<String> result = new ArrayList<>();
        List<CountFilesTask> forks = new ArrayList<>();
        int filesCounter = 0;
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(directory)) {
            for (Path path : paths) {
                if (Files.isRegularFile(path)) {
                    filesCounter++;
                } else if (Files.isDirectory(path)) {
                    CountFilesTask nextTask = new CountFilesTask(path, minFiles);
                    nextTask.fork();
                    forks.add(nextTask);
                }
            }
            if (filesCounter > minFiles) {
                result.add(directory.toString());
            }
        }
        for (CountFilesTask task : forks) {
            result.addAll(task.join());
        }
        return result;
    }
}
