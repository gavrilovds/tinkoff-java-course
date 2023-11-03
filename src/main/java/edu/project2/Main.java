package edu.project2;

import edu.project2.printer.CLIPrinter;
import edu.project2.reader.CLIReader;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        new MazeApplication(new CLIPrinter(), new CLIReader()).run();
    }
}
