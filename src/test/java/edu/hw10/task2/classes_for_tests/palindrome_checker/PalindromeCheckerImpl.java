package edu.hw10.task2.classes_for_tests.palindrome_checker;

import org.apache.commons.lang3.StringUtils;

public class PalindromeCheckerImpl implements PalindromeChecker {

    @Override
    public boolean isPalindrome(String line) {
        return line.equals(StringUtils.reverse(line));
    }
}
