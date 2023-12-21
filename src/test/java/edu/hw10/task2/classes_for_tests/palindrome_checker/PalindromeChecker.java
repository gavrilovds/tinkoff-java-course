package edu.hw10.task2.classes_for_tests.palindrome_checker;

import edu.hw10.task2.annotation.Cache;

public interface PalindromeChecker {

    @Cache(persist = true)
    boolean isPalindrome(String line);
}
