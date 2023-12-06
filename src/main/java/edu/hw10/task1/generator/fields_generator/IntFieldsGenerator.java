package edu.hw10.task1.generator.fields_generator;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class IntFieldsGenerator implements FieldsGenerator {

    @Override
    public Object generate(Annotation[] annotations) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (int) ((Min) annotation).value();
            } else if (annotation instanceof Max) {
                max = (int) ((Max) annotation).value();
            }
        }
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
