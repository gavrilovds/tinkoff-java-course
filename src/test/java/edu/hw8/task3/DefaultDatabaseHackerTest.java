package edu.hw8.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.*;

public class DefaultDatabaseHackerTest {

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
                    put("bbb43e00019fee3d242a2cdea96e74ab", "sdfgw");
                    put("dcd95dac6ffef3b990539fb12d82694d", "sffwg");
                    put("3d6f22549cbf2323256eabf091dad45b", "sdfgergw");
                    put("9f4f0241dfe50c1f7c8246e5280f5359", "grwe");
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
        DatabaseHacker hacker = new DefaultDatabaseHacker(db);
        Map<String, String> actual = hacker.hack();
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected);
    }
}
