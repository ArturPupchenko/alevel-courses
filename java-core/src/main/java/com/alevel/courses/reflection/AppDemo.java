package com.alevel.courses.reflection;


import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class AppDemo {

    public static void main(String[] args) {
        AppProperties appProperties = new AppProperties();
        initFieldsFromPropFile(appProperties);
        System.out.println(appProperties.toString());
    }


    public static void initFieldsFromPropFile(AppProperties appProperties) {
        try {
            Properties props = loadProperties();

            Class<? extends AppProperties> classOfInstance = appProperties.getClass();

            Field[] fields = classOfInstance.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                field.setAccessible(true);
                PropertyKey propertyKeyAnnotation = field.getAnnotation(PropertyKey.class);
                if (propertyKeyAnnotation == null) continue;
                else {
                    field.set(appProperties, props.getProperty(fieldName));
                }
            }

        } catch (IllegalAccessException e) {
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
