package edu.project2.printer;

public class CLIPrinter implements Printer {

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    @Override
    public void print(String line) {
        System.out.println(line);
    }
}
