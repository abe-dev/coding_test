package com.abedev;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by home on 22.12.2016.
 */
@RunWith(JUnit4.class)
public class WordCounterShould {
    private static final String TMP_FILE = "TEST_TEMP_FILE.TXT";
    @Before
    public void setUp() {
        try(InputStream is = WordCounter.class.getClassLoader().getResourceAsStream("test.txt");)
        {
            Files.copy(is,Paths.get(TMP_FILE), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    public void doSomething() {
        WordCounter.processFile(Paths.get(TMP_FILE),5);
    }

    @After
    public void tearDown() {
        try {
            Files.deleteIfExists(Paths.get(TMP_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
