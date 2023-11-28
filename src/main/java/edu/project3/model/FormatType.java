package edu.project3.model;

import java.util.Objects;

public enum FormatType {
    ADOC("adoc"),
    MARKDOWN("markdown");

    FormatType(String value) {
        this.value = value;
    }

    private final String value;

    public static FormatType findByValue(String value) {
        for (FormatType formatType : values()) {
            if (Objects.equals(formatType.value, value)) {
                return formatType;
            }
        }
        throw new IllegalArgumentException("wrong value");
    }
}
