package at.renehollander.advancedmanager.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReflectionUtil {

    public static Map<String, Field> getAllFields(Map<String, Field> fields, Class<?> type) {
        fields.putAll(Arrays.asList(type.getDeclaredFields()).stream().collect(Collectors.toMap(Field::getName, Function.identity())));
        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }
        return fields;
    }

    public static Field getField(Class<?> type, String name) {
        try {
            return type.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            if (type.getSuperclass() != null) {
                return getField(type.getSuperclass(), name);
            } else {
                throw new RuntimeException(new NoSuchFieldException(name));
            }
        }
    }
}
