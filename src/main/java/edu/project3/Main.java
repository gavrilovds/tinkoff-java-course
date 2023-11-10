package edu.project3;

import edu.project3.printer.CLIPrinter;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        new LogAnalyzerApplication(args, new CLIPrinter()).run();
    }
}
