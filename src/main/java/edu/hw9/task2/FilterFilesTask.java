package edu.hw9.task2;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import lombok.SneakyThrows;

public class FilterFilesTask extends RecursiveTask<List<String>> {

    private final Path directory;
    private final Predicate<Path> predicate;

    public FilterFilesTask(Path directory, Predicate<Path> predicate) {
        this.predicate = predicate;
        this.directory = directory;
    }

    @Override
    @SneakyThrows
    protected List<String> compute() {
        List<String> result = new ArrayList<>();
        List<FilterFilesTask> forks = new ArrayList<>();
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(directory)) {
            for (Path path : paths) {
                if (Files.isRegularFile(path)) {
                    if (predicate.test(path)) {
                        result.add(path.toString());
                    }
                } else if (Files.isDirectory(path)) {
                    FilterFilesTask task = new FilterFilesTask(path, predicate);
                    task.fork();
                    forks.add(task);
                }
            }
        }
        for (FilterFilesTask fork : forks) {
            result.addAll(fork.join());
        }
        return result;
    }
}
