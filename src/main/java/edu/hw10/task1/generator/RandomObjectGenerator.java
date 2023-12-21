package edu.hw10.task1.generator;

import edu.hw10.task1.generator.fields_generator.BooleanFieldsGenerator;
import edu.hw10.task1.generator.fields_generator.ByteFieldsGenerator;
import edu.hw10.task1.generator.fields_generator.DoubleFieldsGenerator;
import edu.hw10.task1.generator.fields_generator.FieldsGenerator;
import edu.hw10.task1.generator.fields_generator.FloatFieldsGenerator;
import edu.hw10.task1.generator.fields_generator.IntFieldsGenerator;
import edu.hw10.task1.generator.fields_generator.LongFieldsGenerator;
import edu.hw10.task1.generator.fields_generator.ShortFieldsGenerator;
import edu.hw10.task1.generator.fields_generator.StringFieldsGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;

public class RandomObjectGenerator {

    private static final Map<Class, FieldsGenerator> GENERATORS = new HashMap<>();

    static {
        GENERATORS.put(int.class, new IntFieldsGenerator());
        GENERATORS.put(String.class, new StringFieldsGenerator());
        GENERATORS.put(boolean.class, new BooleanFieldsGenerator());
        GENERATORS.put(byte.class, new ByteFieldsGenerator());
        GENERATORS.put(double.class, new DoubleFieldsGenerator());
        GENERATORS.put(float.class, new FloatFieldsGenerator());
        GENERATORS.put(long.class, new LongFieldsGenerator());
        GENERATORS.put(short.class, new ShortFieldsGenerator());
    }

    public <T> T nextObject(Class<T> className, String fabricMethodName) {
        return processClass(className, fabricMethodName);
    }

    public <T> T nextObject(Class<T> className) {
        return nextObject(className, null);
    }

    @SneakyThrows
    private <T> T processClass(Class<T> className, String fabricMethodName) {
        if (fabricMethodName != null) {
            Method method = getFabricMethod(className, fabricMethodName);
            if (method == null) {
                throw new IllegalArgumentException("No method with name %s".formatted(fabricMethodName));
            }
            Object[] arguments = initArguments(method.getParameters());
            return className.cast(method.invoke(null, arguments));
        }
        Constructor<T> constructor = getConstructor(className);
        Object[] arguments = initArguments(constructor.getParameters());
        return className.cast(constructor.newInstance(arguments));
    }

    private Object[] initArguments(Parameter[] parameters) {
        Object[] arguments = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            arguments[i] = GENERATORS.get(parameters[i].getType()).generate(parameters[i].getAnnotations());
        }
        return arguments;
    }

    private <T> Method getFabricMethod(Class<T> className, String fabricMethodName) {
        for (Method method : className.getDeclaredMethods()) {
            if (method.getName().equals(fabricMethodName)) {
                return method;
            }
        }
        return null;
    }

    private <T> Constructor<T> getConstructor(Class<T> className) {
        Constructor maxArgsConstructor;
        try {
            maxArgsConstructor = className.getDeclaredConstructors()[0];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("no constuctors for class %s".formatted(className.getName()));
        }
        for (Constructor constructor : className.getDeclaredConstructors()) {
            if (constructor.getParameterCount() > maxArgsConstructor.getParameterCount()) {
                maxArgsConstructor = constructor;
            }
        }
        return maxArgsConstructor;
    }
}
