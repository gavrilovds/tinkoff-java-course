package edu.hw6.task3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.*;

public class FilterUtilsTest {

    @TempDir
    private Path tempDir;
    private List<Path> allPaths;

    @BeforeEach
    public void initFiles() {
        allPaths = new ArrayList<>();
        try {
            allPaths.add(Files.writeString(Files.createTempFile(tempDir, "ferw", ".txt"), "test text"));
            allPaths.add(Files.createTempFile(tempDir, "eiziz", ".txt"));
            allPaths.add(Files.createTempFile(tempDir, "nb1", ".txt"));
            allPaths.add(Files.createTempFile(tempDir, "nb2", ".txt"));
            allPaths.add(Files.write(
                Files.createTempFile(tempDir, "png_test", ".png"),
                new byte[] {(byte) 0x89, 'P', 'N', 'G'}
            ));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("#regexContains test")
    public void regexContains_shouldReturnFilteredPaths() {
        DirectoryStream.Filter<Path> filter = FilterUtils.regexContains("nb\\d");
        List<Path> actual = new ArrayList<>();
        try (DirectoryStream<Path> path = Files.newDirectoryStream(tempDir, filter)) {
            path.forEach(actual::add);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThat(actual).containsExactlyInAnyOrder(allPaths.get(2), allPaths.get(3));
    }

    @Test
    @DisplayName("#globMatches test")
    public void globMatches_shouldReturnFilteredPaths() {
        DirectoryStream.Filter<Path> filter = FilterUtils.globMatches("*.png");
        List<Path> actual = new ArrayList<>();
        try (DirectoryStream<Path> path = Files.newDirectoryStream(tempDir, filter)) {
            path.forEach(actual::add);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThat(actual).containsExactlyInAnyOrder(allPaths.get(4));
    }

    @Test
    @DisplayName("#largerThan test")
    public void largerThan_shouldReturnFilteredPaths() {
        DirectoryStream.Filter<Path> filter = FilterUtils.largerThan(7);
        List<Path> actual = new ArrayList<>();
        try (DirectoryStream<Path> path = Files.newDirectoryStream(tempDir, filter)) {
            path.forEach(actual::add);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThat(actual).containsExactlyInAnyOrder(allPaths.get(0));
    }

    @Test
    @DisplayName("#magicNumber test")
    public void magicNumber_shouldReturnFilteredPaths() {
        DirectoryStream.Filter<Path> filter = FilterUtils.magicNumber(0x89, 'P', 'N', 'G');
        List<Path> actual = new ArrayList<>();
        try (DirectoryStream<Path> path = Files.newDirectoryStream(tempDir, filter)) {
            path.forEach(actual::add);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThat(actual).containsExactlyInAnyOrder(allPaths.get(4));
    }

    @Test
    @DisplayName("Multiple filters test")
    public void filter_shouldReturnFilteredByTwoFiltersPaths() {
        DirectoryStream.Filter<Path> filter = FilterUtils.magicNumber(0x89, 'P', 'N', 'G')
            .and(FilterUtils.largerThan(3));
        List<Path> actual = new ArrayList<>();
        try (DirectoryStream<Path> path = Files.newDirectoryStream(tempDir, filter)) {
            path.forEach(actual::add);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThat(actual).containsExactlyInAnyOrder(allPaths.get(4));
    }
}
