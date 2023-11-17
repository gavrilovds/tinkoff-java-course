package edu.project3.retriever.selector;

import edu.project3.retriever.LocalLogRetriever;
import edu.project3.retriever.LogRetriever;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalRetrieverSelector extends RetrieverSelector {

    @Override
    public LogRetriever selectRetriever(String path) {
        try {
            Paths.get(path);
            return new LocalLogRetriever(path);
        } catch (Exception e) {
            return checkNext(path);
        }
    }
}
