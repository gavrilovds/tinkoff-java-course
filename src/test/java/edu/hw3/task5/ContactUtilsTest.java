package edu.hw3.task5;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.*;

public class ContactUtilsTest {

    private static Stream<Arguments> basicTestInputs() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                SortOrder.ASC,
                List.of(
                    new Contact("Thomas", "Aquinas"),
                    new Contact("Rene", "Descartes"),
                    new Contact("David", "Hume"),
                    new Contact("John", "Locke")
                )
            ),
            Arguments.of(new String[] {"Paul", "Leonhard", "Carl"}, SortOrder.DESC,
                List.of(
                    new Contact("Paul"),
                    new Contact("Leonhard"),
                    new Contact("Carl")
                )
            )
        );
    }

    private static Stream<Arguments> invalidTestInputs() {
        return Stream.of(
            Arguments.of((Object) new String[] {"Dmitriy Gavrilov Sergeevich"}),
            Arguments.of((Object) new String[] {"Isaac Asim0v"}),
            Arguments.of((Object) new String[] {"1saac Asimov"})
        );
    }

    @ParameterizedTest
    @MethodSource("basicTestInputs")
    @DisplayName("Basic tests for #parseContacts")
    public void arrayOfPeopleInfo_shouldReturnSortedListOfContacts(
        String[] testPeopleInfo,
        SortOrder sortOrder,
        List<Contact> expected
    ) {
        List<Contact> actual = ContactUtils.parseContacts(testPeopleInfo, sortOrder);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Null and empty test for #parseContacts")
    public void arrayOfPeopleInfo_shouldReturnEmptyList_whenArrayIsNullOrEmpty(String[] testPeopleInfo) {
        List<Contact> actual = ContactUtils.parseContacts(testPeopleInfo, SortOrder.ASC);
        assertThat(actual).isEqualTo(Collections.emptyList());
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null SortOrder test for #parseContacts")
    public void sortOrder_shouldReturnAscSortedListOfContacts(SortOrder testSortOrder) {
        List<Contact> actual = ContactUtils.parseContacts(new String[] {"Leon", "Carl"}, testSortOrder);
        assertThat(actual).isEqualTo(List.of(new Contact("Carl"), new Contact("Leon")));
    }

    @ParameterizedTest
    @MethodSource("invalidTestInputs")
    @DisplayName("Invalid people info test for #parseContacts")
    public void arrayOfPeopleInfo_shouldThrowException_whenArrayContainsInvalidInfo(String[] testPeopleInfo) {
        assertThatThrownBy(() -> ContactUtils.parseContacts(testPeopleInfo, SortOrder.ASC)).isInstanceOf(
            IllegalArgumentException.class);
    }
}
