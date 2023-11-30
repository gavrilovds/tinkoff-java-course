package edu.hw9.task2;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FilesProcessor {

    @SneakyThrows
    public static List<String> findDirectoriesWithMoreThanNumberFiles(Path root, int number) {
        List<String> result;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        result = forkJoinPool.invoke(new CountFilesTask(root, number));
        forkJoinPool.shutdown();
        return result;
    }

    public static List<String> filterFilesByPredicate(Path root, Predicate<Path> predicate) {
        List<String> result;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        result = forkJoinPool.invoke(new FilterFilesTask(root, predicate));
        forkJoinPool.shutdown();
        return result;
    }
}
