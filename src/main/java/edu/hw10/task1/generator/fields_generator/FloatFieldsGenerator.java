package edu.hw10.task1.generator.fields_generator;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class FloatFieldsGenerator implements FieldsGenerator {

    @Override
    public Object generate(Annotation[] annotations) {
        float min = Float.MIN_VALUE;
        float max = Float.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = ((Min) annotation).value();
            } else if (annotation instanceof Max) {
                max = ((Max) annotation).value();
            }
        }
        return ThreadLocalRandom.current().nextFloat(min, max);
    }
}
