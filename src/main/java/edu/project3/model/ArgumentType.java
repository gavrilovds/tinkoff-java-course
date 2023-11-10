package edu.project3.model;

import lombok.Getter;

@Getter
public enum ArgumentType {
    PATH("--path"),
    FROM("--from"),
    TO("--to"),
    FORMAT("--format");

    ArgumentType(String argument) {
        this.argument = argument;
    }

    private final String argument;
}
