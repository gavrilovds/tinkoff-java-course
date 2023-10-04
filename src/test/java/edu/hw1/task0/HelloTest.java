package edu.hw1.task0;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HelloTest {
    @Test
    @DisplayName("Тест вывода \"Привет, мир!\" через Logger" )
    public void shouldPrintHelloWorld(){
        HelloWorld.printHello();
    }
}
