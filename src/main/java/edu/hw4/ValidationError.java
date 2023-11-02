package edu.hw4;

import lombok.AllArgsConstructor;
import lombok.Getter;

public record ValidationError(ErrorType errorType) {

    @Getter
    @AllArgsConstructor
    enum ErrorType {

        AGE("Age should be greater than 0"),

        HEIGHT("Height should be greater than 0"),

        WEIGHT("Weight should be greater than 0");
        private final String errorMessage;
    }
}
