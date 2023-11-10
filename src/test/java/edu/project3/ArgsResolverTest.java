package edu.project3;

import edu.project3.model.Argument;
import edu.project3.model.ArgumentType;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.*;

public class ArgsResolverTest {

    private static Stream<Arguments> inputsForResolveTest() {
        return Stream.of(
            Arguments.of(
                new String[] {"--path", "test.txt"},
                List.of(new Argument(ArgumentType.PATH, "test.txt"))
            ),
            Arguments.of(
                new String[] {"--path", "test.txt", "test2.txt"},
                List.of(new Argument(ArgumentType.PATH, "test.txt test2.txt"))
            ),
            Arguments.of(
                new String[] {"--path", "test.txt", "test2.txt", "--format", "adoc"},
                List.of(
                    new Argument(ArgumentType.PATH, "test.txt test2.txt"),
                    new Argument(ArgumentType.FORMAT, "adoc")
                )
            ),
            Arguments.of(
                new String[] {"--path", "test.txt", "test2.txt", "--format", "adoc", "--to", "24.03.22"},
                List.of(
                    new Argument(ArgumentType.PATH, "test.txt test2.txt"),
                    new Argument(ArgumentType.FORMAT, "adoc"),
                    new Argument(ArgumentType.TO, "24.03.22")
                )
            ),
            Arguments.of(
                new String[] {"--path", "test.txt", "test2.txt", "--format", "adoc", "--to", "24.03.22", "--from",
                    "23.02.22"},
                List.of(
                    new Argument(ArgumentType.PATH, "test.txt test2.txt"),
                    new Argument(ArgumentType.FORMAT, "adoc"),
                    new Argument(ArgumentType.TO, "24.03.22"),
                    new Argument(ArgumentType.FROM, "23.02.22")
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForResolveTest")
    @DisplayName("#resolve test")
    public void resolve_shouldReturnListOfResolvedArgs(String[] testArgs, List<Argument> expected) {
        List<Argument> actual = new ArgsResolver().resolve(testArgs);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("#resolve invalid input test #1")
    public void resolve_shouldThrowExceptionWhenArgsSizeLessThan2() {
        assertThatThrownBy(() -> new ArgsResolver().resolve(new String[] {"--path"})).isInstanceOf(
            IllegalArgumentException.class);
    }
    @Test
    @DisplayName("#resolve invalid input test #2")
    public void resolve_shouldThrowExceptionWhenFirstParameterIsNotPath() {
        assertThatThrownBy(() -> new ArgsResolver().resolve(new String[] {"--format"})).isInstanceOf(
            IllegalArgumentException.class);
    }
    @Test
    @DisplayName("#resolve invalid input test #3")
    public void resolve_shouldThrowExceptionWhenSomeParameterHasNoInfo() {
        assertThatThrownBy(() -> new ArgsResolver().resolve(new String[] {"--path", "text.txt", "--format"})).isInstanceOf(
            IllegalArgumentException.class);
    }
}
