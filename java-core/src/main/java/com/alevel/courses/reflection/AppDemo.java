package com.alevel.courses.reflection;


public class AppDemo {

    public static void main(String[] args) {
        AppProperties appProperties = new AppProperties();
        FiledInitializer.initFieldsFromPropFile(appProperties);
        System.out.println(appProperties.toString());
    }
}







