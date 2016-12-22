package com.abedev;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by home on 21.12.2016.
 */
@RunWith(JUnit4.class)
public class PalindromeCheckerShould {
    private static PalindromeChecker checker;
    @BeforeClass
    public static void setUp(){
        checker = new PalindromeChecker();
    }

    @Test
    public void return_true_on_12321() {
        assertThat(checker.test("12321"), is(true));
        ;
    }
    @Test
    public void return_false_on_12311() {
        assertThat(checker.test("12311"), is(false));
        ;
    }
    @Test
    public void return_true_on_1111221111() {
        assertThat(checker.test("1111221111"), is(true));
        ;
    }

}
