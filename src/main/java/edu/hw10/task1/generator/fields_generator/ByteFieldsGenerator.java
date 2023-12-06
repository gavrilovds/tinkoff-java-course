package edu.hw10.task1.generator.fields_generator;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class ByteFieldsGenerator implements FieldsGenerator {

    @Override
    public Object generate(Annotation[] annotations) {
        byte min = Byte.MIN_VALUE;
        byte max = Byte.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (byte) ((Min) annotation).value();
            } else if (annotation instanceof Max) {
                max = (byte) ((Max) annotation).value();
            }
        }
        return (byte) ThreadLocalRandom.current().nextInt(min, max);
    }
}
