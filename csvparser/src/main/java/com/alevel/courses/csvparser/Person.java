package com.alevel.courses.csvparser;

import com.alevel.courses.csvparser.PropertyKey;

public class Person {

    public enum Gender {
        male,
        female
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", age=" + age +
                ", gender=" + gender +
                ", occupation=" + occupation +
                '}';
    }

    @PropertyKey("name")
    private String name;

    @PropertyKey("age")
    private int age;

    @PropertyKey("gender")
    private Gender gender;

    @PropertyKey("occupation")
    private String occupation;

    public Person() {
    }

    public Person(String name, int age, Gender gender, String occupation) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }


    public String getOccupation() {
        return occupation;
    }
}
