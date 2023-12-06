package edu.hw10.task1.generator.fields_generator;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;
import java.lang.annotation.Annotation;
import org.apache.commons.lang3.RandomStringUtils;

public class StringFieldsGenerator implements FieldsGenerator {

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 8;

    @Override
    public Object generate(Annotation[] annotations) {
        int minLength = MIN_LENGTH;
        int maxLength = MAX_LENGTH;
        boolean isNotNull = false;
        for (Annotation annotation : annotations) {
            if (annotation instanceof NotNull) {
                isNotNull = true;
            } else if (annotation instanceof Min) {
                minLength = (int) ((Min) annotation).value();
            } else if (annotation instanceof Max) {
                maxLength = (int) ((Max) annotation).value();
            }
        }
        if (!isNotNull) {
            return null;
        }
        return minLength < maxLength ? RandomStringUtils.randomAlphabetic(minLength, maxLength)
            : RandomStringUtils.randomAlphabetic(maxLength, maxLength);
    }
}
