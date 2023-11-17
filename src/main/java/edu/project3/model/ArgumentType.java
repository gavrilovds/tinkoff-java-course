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

    public static ArgumentType findByArgument(String argument) throws IllegalArgumentException {
        for (ArgumentType type : values()) {
            if (type.getArgument().equals(argument)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid argument: " + argument);
    }
}
