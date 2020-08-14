package com.alevel.courses.reflection;

public class AppProperties {

    @PropertyKey("book")
    public String book;

    @PropertyKey("author")
    public String author;

    public long sales = 0;

    @Override
    public String toString() {
        return "AppProperties{" +
                "book='" + book + '\'' +
                ", author='" + author + '\'' +
                ", sales=" + sales +
                '}';
    }
}
