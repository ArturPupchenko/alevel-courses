package com.alevel.courses.reflection;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class FiledInitializer {

    public static void initFieldsFromPropFile(Object obj) {
        try {
            Properties props = loadProperties();

            Class<? extends Object> classOfInstance = obj.getClass();

            Field[] fields = classOfInstance.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                field.setAccessible(true);
                PropertyKey propertyKeyAnnotation = field.getAnnotation(PropertyKey.class);
                if (propertyKeyAnnotation == null) continue;
                else {
                    field.set(obj, props.getProperty(fieldName));
                }
            }

        } catch (
                IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private static Properties loadProperties() {

        Properties props = new Properties();

        try (InputStream input = AppDemo.class.getResourceAsStream("/app.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return props;
    }
}

