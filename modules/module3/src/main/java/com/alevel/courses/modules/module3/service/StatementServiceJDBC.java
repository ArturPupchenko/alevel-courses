package com.alevel.courses.modules.module3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class StatementServiceJDBC {
//    2) Экспорт выписки по счету в csv формате
//    (должен создавать выходной файл .csv).
//    Выписка должна включать список операций за определенный период,
//    а также общую сумму доходов и сальдо.

    private static final Logger log = LoggerFactory.getLogger(StatementServiceJDBC.class);

    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/module3-db";
    private String USER = "username";
    private String PASS = "password";
    private Connection connection;

    public StatementServiceJDBC(String username, String password) {
        this.USER = username;
        this.PASS = password;
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            log.info("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            log.info("Connected database successfully.");
            this.connection = connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getStatements(Long userId, Long accountId, Timestamp from, Timestamp to) {
        try (PreparedStatement getOperations = connection.prepareStatement(
                "SELECT id FROM operations WHERE timestamp > ? AND timestamp < ?")) {

            getOperations.setTimestamp(1, from);
            getOperations.setTimestamp(2, to);

            ResultSet resultSet = getOperations.executeQuery();
            List<Long> operationsId = new ArrayList<>();
            while (resultSet.next()) {
//                operationsId.add(resultSet.getLong());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}