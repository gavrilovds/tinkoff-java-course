package edu.project3.retriever;

import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LocalLogRetriever implements LogRetriever {

    private static final String LOGS_DIRECTORY = "logs";
    private final String path;

    public LocalLogRetriever(String path) {
        this.path = path;
    }

    @Override
    public List<String> retrieveLogs() {
        List<String> logs = new ArrayList<>();
        try (Stream<Path> pathStream = Files.walk(
            Paths.get(LOGS_DIRECTORY),
            Integer.MAX_VALUE,
            FileVisitOption.FOLLOW_LINKS
        )) {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + path);
            pathStream
                .filter(Files::isRegularFile)
                .filter(pathMatcher::matches)
                .forEach(el -> logs.addAll(getLogsFromFile(el)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return logs;
    }

    private List<String> getLogsFromFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
