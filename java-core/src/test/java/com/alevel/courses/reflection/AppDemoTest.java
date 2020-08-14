package com.alevel.courses.reflection;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppDemoTest {

    @Test
    public void test_canSetFieldsWithAnnotation() {
        AppProperties appProperties = new AppProperties();
        FiledInitializer.initFieldsFromPropFile(appProperties);
        assertEquals(appProperties.book, "MyBook");
        assertEquals(appProperties.author, "MyAuthor");
    }
}