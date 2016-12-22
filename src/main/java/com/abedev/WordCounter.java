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
public class WordCounter {
    private static final String TMP_FILE = "TEST_TEMP_FILE.TXT";
    public static final Path TMP_FILE_PATH = Paths.get(TMP_FILE);

    public static void main(String... args) {
        prepareFile();
        processFile(Paths.get(TMP_FILE),5);
        deleteFile();
    }

    private static void prepareFile() {
        try(InputStream is = WordCounter.class.getClassLoader().getResourceAsStream("test.txt");)
        {
            Files.copy(is, TMP_FILE_PATH, StandardCopyOption.REPLACE_EXISTING);
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
            Map.Entry.comparingByValue().reversed();
            words
                    .entrySet()
                    .stream()
                    .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                    .limit(topN)
                    .forEach(pair -> System.out.println(String.format("%1$s - %2$d", pair.getKey(), pair.getValue())));
        } catch (IOException ex) {
            throw new RuntimeException("Error reading file!!!",ex);
        }
    }

    private static void deleteFile() {
        try {
            Files.deleteIfExists(Paths.get(TMP_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
