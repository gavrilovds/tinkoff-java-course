package edu.hw10.task1.generator.fields_generator;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class ShortFieldsGenerator implements FieldsGenerator {

    @Override
    public Object generate(Annotation[] annotations) {
        short min = Short.MIN_VALUE;
        short max = Short.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (short) ((Min) annotation).value();
            } else if (annotation instanceof Max) {
                max = (short) ((Max) annotation).value();
            }
        }
        return (short) ThreadLocalRandom.current().nextInt(min, max);
    }
}
