package edu.hw10.task1.generator.fields_generator;

import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class BooleanFieldsGenerator implements FieldsGenerator {

    @Override
    public Object generate(Annotation[] annotations) {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
