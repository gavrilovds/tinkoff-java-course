package edu.project3;

import edu.project3.retriever.LocalLogRetriever;
import edu.project3.retriever.LogRetriever;
import edu.project3.retriever.UrlLogRetriever;
import java.net.URI;
import java.net.URL;

public final class RetrieverHelper {

    private RetrieverHelper() {
    }

    public static LogRetriever getCorrectRetriever(String source) {
        try {
            new URI(source);
            return new UrlLogRetriever(source);
        } catch (Exception e) {
            return new LocalLogRetriever(source);
        }
    }
}
