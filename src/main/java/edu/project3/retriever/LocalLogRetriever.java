package edu.project3.retriever;

import java.util.ArrayList;
import java.util.List;

public class LocalLogRetriever implements LogRetriever {

    private final String path;

    public LocalLogRetriever(String path) {
        this.path = path;
    }

    @Override
    public List<String> retrieveLogs() {
        List<String> logs = new ArrayList<>();

        return logs;
    }
}
