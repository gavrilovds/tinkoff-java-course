package edu.hw8.task3;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ParallelDatabaseHackerTest {

    private static Stream<Arguments> inputsForHackTest() {
        return Stream.of(
            Arguments.of(
                new HashMap<String, String>() {{
                    put("0f5b25cd58319cde0e6e33715b66db4c", "user1");
                    put("1b18e2ffe3a99ea9486ba69c02c72763", "user2");
                }},
                new HashMap<String, String>() {{
                    put("user1", "dima");
                    put("user2", "dmitr");
                }}
            )
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForHackTest")
    @DisplayName("#hack test")
    public void hack_shouldReturnMapWithUsernameAndHackedPassword(
        Map<String, String> db,
        Map<String, String> expected
    ) {
        DatabaseHacker hacker = new ParallelDatabaseHacker(db);
        Map<String, String> actual = hacker.hack();
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected);
    }
}
