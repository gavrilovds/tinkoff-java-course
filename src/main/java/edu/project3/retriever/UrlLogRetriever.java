package edu.project3.retriever;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class UrlLogRetriever implements LogRetriever {

    private final String url;

    public UrlLogRetriever(String url) {
        this.url = url;
    }

    @Override
    public List<String> retrieveLogs() {
        List<String> logs = new ArrayList<>();
        try {
            URI logSource = new URI(url);

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(logSource.toURL()
                .openStream()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    logs.add(line);
                }
                return logs;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
