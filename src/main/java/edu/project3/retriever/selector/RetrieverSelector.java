package edu.project3.retriever.selector;

import edu.project3.retriever.LogRetriever;

public abstract class RetrieverSelector {

    private RetrieverSelector nextRetriever;

    public static RetrieverSelector link(RetrieverSelector first, RetrieverSelector... chain) {
        RetrieverSelector head = first;
        for (RetrieverSelector selector : chain) {
            head.nextRetriever = selector;
            head = selector;
        }
        return first;
    }

    public abstract LogRetriever selectRetriever(String path);

    protected LogRetriever checkNext(String path) {
        if (nextRetriever == null) {
            throw new IllegalArgumentException("wrong path for logs");
        }
        return nextRetriever.selectRetriever(path);
    }
}
