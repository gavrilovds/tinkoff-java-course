package edu.project2;

import edu.project2.util.MessagesUtils;

public class MazeApplication {

    private static final String CORRECT_SIZE_REGEX = "^(10|1[0-9]|[2-3][0-9]|40) (10|1[0-9]|[2-3][0-9]|40)$";
    private final Reader reader = new Reader();
    private final Printer printer = new Printer();

    public MazeApplication() {
    }

    public void run() {
        printer.print(MessagesUtils.HELLO_MESSAGE);
        printer.print(MessagesUtils.INPUT_MAZE_SIZE_MESSAGE);
        String userInput = reader.read();
        if (!userInput.matches(CORRECT_SIZE_REGEX)) {
            throw new IllegalArgumentException("Wrong input");
        }
        var splitedInput = userInput.split(" ");
        int height = Integer.parseInt(splitedInput[0]);
        int width = Integer.parseInt(splitedInput[1]);

    }

}
