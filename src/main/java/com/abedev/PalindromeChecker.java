package com.abedev;

import java.util.function.Predicate;

/**
 * Created by home on 21.12.2016.
 */
public final class PalindromeChecker implements Predicate<String> {

    @Override
    public boolean test(String sourceString) {
        if (sourceString == null)
            throw new IllegalArgumentException("Source string can't be null!");
        boolean isPalindrome = true;
        final int fullLength = sourceString.length();
        final int halfLength = fullLength / 2;
        for (int i = 0; i < halfLength; i++) {
            if (sourceString.charAt(i) != sourceString.charAt(fullLength - i - 1)) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }
}
