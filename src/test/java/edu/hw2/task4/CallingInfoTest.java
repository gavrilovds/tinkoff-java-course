package edu.hw2.task4;

import java.lang.reflect.Method;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class CallingInfoTest {

    private static Stream<Supplier<CallingInfo>> suppliers() {
        return Stream.of(
            CallingInfoTest::firstMethod,
            CallingInfoTest::secondMethod,
            CallingInfoTest::thirdMethod,
            CallingInfoTest::fourthMethod
        );
    }

    @ParameterizedTest
    @MethodSource("suppliers")
    @DisplayName("Тестирование callingInfo на методах с разной глубиной вызов")
    public void method_shouldReturnCallingInfoObjectWithNameOfTheLastCalledMethod(Supplier<CallingInfo> supplier) {
        CallingInfo callingInfo = supplier.get();
        assertThat(callingInfo)
            .extracting("methodName", "className")
            .containsExactly("firstMethod", "edu.hw2.task4.CallingInfoTest");
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
