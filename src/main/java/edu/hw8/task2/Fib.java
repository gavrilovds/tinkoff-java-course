package edu.hw8.task2;

import lombok.SneakyThrows;

public class Fib {

    @SneakyThrows

    public static void main(String[] args) {
        try (FixedThreadPool threadPool = FixedThreadPool.create(3)) {
            for (int i = 0; i <= 30; i++) {
                final int fibIndex = i;
                threadPool.execute(() -> {
                    long result = nthFibonacciTerm(fibIndex);
                    System.out.println("Fibonacci(" + fibIndex + ") = " + result);
                });
            }
        }
    }

    public static int nthFibonacciTerm(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int n0 = 0;
        int n1 = 1;
        int tempNthTerm;
        for (int i = 2; i <= n; i++) {
            tempNthTerm = n0 + n1;
            n0 = n1;
            n1 = tempNthTerm;
        }
        return n1;
    }
}
