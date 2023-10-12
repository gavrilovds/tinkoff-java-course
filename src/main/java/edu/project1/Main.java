package edu.project1;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        new ConsoleHangman(new Session(new InMemoryDictionary(new String[] {"tinkoff", "java", "world"}))).run();
    }

}
