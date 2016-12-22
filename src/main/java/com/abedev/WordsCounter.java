package com.abedev;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by home on 21.12.2016.
 */
public class WordsCounter {
    private static final String TMP_FILE = "TEST_TEMP_FILE.TXT";

    public static void main(String... args) {
        Path file = prepareFile();
        processFile(file,5);
        deleteFile(file);
    }

    private static Path prepareFile() {
        try(InputStream is = WordsCounter.class.getClassLoader().getResourceAsStream("test.txt");)
        {
            Path path = Paths.get(TMP_FILE);
            Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);
            return path;
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    static void processFile(Path path, int topN) {
        try (final Stream<String> lines = Files.lines(path)) {
            final Map<String, Long> words = lines
                    .flatMap(s -> Stream.of(s.split(" ")))
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            words
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .limit(topN)
                    .forEach(pair -> System.out.println(String.format("%1$s - %2$d", pair.getKey(), pair.getValue())));
        } catch (IOException ex) {
            throw new RuntimeException("Error reading file!!!",ex);
        }
    }

    private static void deleteFile(Path file) {
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
