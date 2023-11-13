package edu.hw6.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class HackerNewsTest {

    @Test
    @DisplayName("#getHackerNewsTopStories test")
    public void getHackerNewsTopStories_shouldReturnLongArrayOfTopStoriesIDs() {
        long[] actual = new HackerNews().getHackerNewsTopStories();
        assertThat(actual).isNotEmpty();
    }

    @Test
    @DisplayName("#getNewsTitle test")
    public void getNewsTitle_shouldReturnNewsTitleOfGivenId() {
        String actual = new HackerNews().getNewsTitle(1);
        assertThat(actual).isNotEmpty();
    }

    @Test
    @DisplayName("#getNewsTitle wrong id test")
    public void getNewsTitle_shouldReturnEmptyString_whenGivenIdIsWrong() {
        String actual = new HackerNews().getNewsTitle(-1);
        assertThat(actual).isEmpty();
    }
}
