package com.alevel.courses.module3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        // Init DB
        // new MockDataLoader().init();
        JPAVersion.run(new String[]{"1", "postgres", "3176rz1t"});
        JDBCVersion.run(new String[]{"1", "postgres", "3176rz1t"});
    }
}

