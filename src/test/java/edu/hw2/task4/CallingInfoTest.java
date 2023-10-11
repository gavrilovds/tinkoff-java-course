package edu.hw2.task4;

import java.lang.reflect.Method;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class CallingInfoTest {

    private static Stream<Method> methods() {
        try {
            return Stream.of(
                new CallingInfoTest().getClass().getDeclaredMethod("firstMethod"),
                new CallingInfoTest().getClass().getDeclaredMethod("secondMethod"),
                new CallingInfoTest().getClass().getDeclaredMethod("thirdMethod"),
                new CallingInfoTest().getClass().getDeclaredMethod("fourthMethod")
            );
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    @ParameterizedTest
    @MethodSource("methods")
    @DisplayName("Тестирование callingInfo на методах с разной глубиной вызов")
    public void method_shouldReturnCallingInfoObjectWithNameOfTheLastCalledMethod(Method method) {
        try {
            assertThat(((CallingInfo) method.invoke(null)).methodName()).isEqualTo("firstMethod");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static CallingInfo firstMethod() {
        return CallingInfo.callingInfo();
    }

    private static CallingInfo secondMethod() {
        return firstMethod();
    }

    private static CallingInfo thirdMethod() {
        return secondMethod();
    }

    private static CallingInfo fourthMethod() {
        return thirdMethod();
    }

}
