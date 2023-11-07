package edu.project3.filter;

import edu.project3.model.NginxLog;

public abstract class LogFilter {

    private LogFilter next;

    public static LogFilter link(LogFilter first, LogFilter... chain) {
        LogFilter head = first;
        for (LogFilter filter : chain) {
            head.next = filter;
            head = filter;
        }
        return first;
    }

    public abstract boolean hasPassedFilter(NginxLog log);

    protected boolean checkNext(NginxLog log) {
        if (next == null) {
            return true;
        }
        return next.hasPassedFilter(log);
    }
}
