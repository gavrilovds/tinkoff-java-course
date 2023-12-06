package edu.hw10.task1.generator.fields_generator;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class LongFieldsGenerator implements FieldsGenerator {

    @Override
    public Object generate(Annotation[] annotations) {
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = ((Min) annotation).value();
            } else if (annotation instanceof Max) {
                max = ((Max) annotation).value();
            }
        }
        return ThreadLocalRandom.current().nextLong(min, max);
    }
}
