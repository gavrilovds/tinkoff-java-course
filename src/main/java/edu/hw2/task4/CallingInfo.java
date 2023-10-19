package edu.hw2.task4;

public record CallingInfo(String className, String methodName) {

    public static CallingInfo callingInfo() {
        try {
            throw new Exception();
        } catch (Throwable throwable) {
            StackTraceElement previousStackTraceElement =
                throwable.getStackTrace()[1];
            return new CallingInfo(previousStackTraceElement.getClassName(), previousStackTraceElement.getMethodName());
        }
    }
}
