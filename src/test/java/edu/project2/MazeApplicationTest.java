package edu.project2;

import edu.project2.printer.Printer;
import edu.project2.reader.Reader;
import edu.project2.util.MessagesUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class MazeApplicationTest {

    private final List<String> output = new ArrayList<>();
    private final Queue<Integer> input = new LinkedList<>();
    private final Reader reader = input::poll;
    private final Printer printer = output::add;
    private MazeApplication application;

    @BeforeEach
    public void resetData() {
        output.clear();
        input.clear();
        application = new MazeApplication(printer, reader);
    }

    @Test
    @DisplayName("#run invalid input test#1")
    public void run_shouldPrintCorrectDataWhenMazeInputIsIncorrect() {
        input.addAll(List.of(23, 24, 1, 1, 1, 1, 13, 13));
        Assertions.assertAll(
            () -> assertThatThrownBy(() -> application.run()).isInstanceOf(IllegalArgumentException.class),
            () -> assertThat(output).containsExactly(
                MessagesUtils.HELLO_MESSAGE,
                MessagesUtils.INPUT_MAZE_SIZE_MESSAGE
            )
        );
    }

    @Test
    @DisplayName("#run invalid input test#2")
    public void run_shouldPrintCorrectDataWhenChooseGeneratorIsIncorrect() {
        input.addAll(List.of(23, 23, 4, 1, 1, 1, 13, 13));
        Assertions.assertAll(
            () -> assertThatThrownBy(() -> application.run()).isInstanceOf(IllegalArgumentException.class),
            () -> assertThat(output).containsExactly(
                MessagesUtils.HELLO_MESSAGE,
                MessagesUtils.INPUT_MAZE_SIZE_MESSAGE,
                MessagesUtils.CHOOSE_GENERATOR_MESSAGE
            )
        );
    }

    @Test
    @DisplayName("#run invalid input test#3")
    public void run_shouldPrintCorrectDataWhenChooseSolverIsIncorrect() {
        input.addAll(List.of(23, 23, 1, 3, 1, 1, 13, 13));
        Assertions.assertAll(
            () -> assertThatThrownBy(() -> application.run()).isInstanceOf(IllegalArgumentException.class),
            () -> assertThat(output).containsExactly(
                MessagesUtils.HELLO_MESSAGE,
                MessagesUtils.INPUT_MAZE_SIZE_MESSAGE,
                MessagesUtils.CHOOSE_GENERATOR_MESSAGE,
                MessagesUtils.CHOOSE_SOLVER_MESSAGE
            )
        );
    }

    @Test
    @DisplayName("#run invalid input test#4")
    public void run_shouldPrintCorrectDataWhenInputCoordinatesIsIncorrect() {
        input.addAll(List.of(23, 23, 1, 1, -1, 1, 13, 13));
        Assertions.assertAll(
            () -> assertThatThrownBy(() -> application.run()).isInstanceOf(IllegalArgumentException.class),
            () -> assertThat(output.subList(5, output.size())).containsExactly(
                MessagesUtils.INPUT_COORDINATES_MESSAGE
            )
        );
    }

    @Test
    @DisplayName("#run valid input test")
    public void run_shouldPrintCorrectDataWhenAllInputIsCorrect() {
        input.addAll(List.of(23, 23, 1, 1, 1, 1, 13, 13));
        application.run();
        Assertions.assertAll(
            () -> assertThat(output.subList(0, 4)).containsExactly(
                MessagesUtils.HELLO_MESSAGE,
                MessagesUtils.INPUT_MAZE_SIZE_MESSAGE,
                MessagesUtils.CHOOSE_GENERATOR_MESSAGE,
                MessagesUtils.CHOOSE_SOLVER_MESSAGE
            ),
            () -> assertThat(output.subList(5, 6)).containsExactly(MessagesUtils.INPUT_COORDINATES_MESSAGE),
            () -> assertThat(output.subList(7, output.size())).containsExactly(MessagesUtils.END_MESSAGE)
        );
    }

}
