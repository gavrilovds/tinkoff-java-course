package edu.hw10.task1.generator.fields_generator;

import java.lang.annotation.Annotation;

public interface FieldsGenerator {

    Object generate(Annotation[] annotations);
}
