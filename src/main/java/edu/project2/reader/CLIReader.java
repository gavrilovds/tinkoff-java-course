package edu.project2.reader;

import java.util.Scanner;

public class CLIReader implements Reader {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int readInt() {
        return scanner.nextInt();
    }
}
