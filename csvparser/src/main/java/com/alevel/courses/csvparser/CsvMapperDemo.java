package com.alevel.courses.csvparser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class CsvMapperDemo {


    public static void main(String[] args) {
        try {
            Optional<CsvTable> csvTable = CsvTable.fromFile(Path.of("csvparser/src/main/resources/csvtest.csv"));
            List<Person> peopleList = new CsvMapper<>(Person.class).createByCsvTable(csvTable.get());
            peopleList.forEach(e -> System.out.println(e.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
