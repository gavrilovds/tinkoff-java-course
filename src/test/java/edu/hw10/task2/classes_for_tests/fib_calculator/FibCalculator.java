package edu.hw10.task2.classes_for_tests.fib_calculator;

import edu.hw10.task2.annotation.Cache;

public interface FibCalculator {

    @Cache
    long getFib(int number);
}
