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
                    put("18e84fa2f549f87a4a3ee3a46e63c3e2", "fwe");
                    put("9a32f4a86b3bcd053642f4c708be27f8", "grs");
                    put("0765c0f7c8e3df239f846c4f78ed1da6", "dfg");
                    put("2354b5f68a4ce8a030eee955639fdd16", "xv");
                    put("b67730ea70c932810107fc830c759597", "drg");
                    put("e748947f38e11631154bc359e77f8dfc", "wrt");
                    put("4c8a9ad866a7072c0a822abf2d7beb4b", "sdfgw");
                    put("27183aacdcb689968f322032550ad33d", "fm");
                }},
                new HashMap<String, String>() {{
                    put("user", "99999");
                }}
                /*new HashMap<String, String>() {{
                    put("0f5b25cd58319cde0e6e33715b66db4c", "user1");
                    put("1b18e2ffe3a99ea9486ba69c02c72763", "user2");
                }},
                new HashMap<String, String>() {{
                    put("user1", "dima");
                    put("user2", "dmitr");
                }}*/
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
