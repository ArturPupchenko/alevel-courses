package com.alevel.courses.modules.module3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.alevel.courses.modules.module3.util.CsvTable.createCsvTableAndWriteItTiFile;

public class StatementServiceJDBC {

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
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            this.connection = connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getStatements(Long userId, Long accountId, Timestamp from, Timestamp to) {

        try (PreparedStatement getOperationsOfCurrentAccountInSelectedTimeRange = connection.prepareStatement(
                "SELECT * FROM operations WHERE operations.timestamp > ? AND operations.timestamp < ? AND operations.account_id = ? ORDER BY operations.timestamp")) {
            getOperationsOfCurrentAccountInSelectedTimeRange.setTimestamp(1, from);
            getOperationsOfCurrentAccountInSelectedTimeRange.setTimestamp(2, to);
            getOperationsOfCurrentAccountInSelectedTimeRange.setLong(3, accountId);
            ResultSet resultSet = getOperationsOfCurrentAccountInSelectedTimeRange.executeQuery();
            List<Long> operationsId = new ArrayList<>();
            List<Long> operationsAmount = new ArrayList<>();
            List<Timestamp> operationsTimestamp = new ArrayList<>();
            while (resultSet.next()) {
                if (resultSet.getLong(4) == accountId) {
                    operationsId.add(resultSet.getLong(1));
                    operationsAmount.add(resultSet.getLong(2));
                    operationsTimestamp.add(resultSet.getTimestamp(3));
                }
            }
            long balanceBeforeFirstOperationInTimeRange = 0;

            try (PreparedStatement getBalanceBeforeRequestedOperations = connection.prepareStatement(
                    "SELECT SUM(operations.amount) FROM operations WHERE operations.timestamp < ? AND operations.account_id = ?")) {
                getBalanceBeforeRequestedOperations.setTimestamp(1, from);
                getBalanceBeforeRequestedOperations.setLong(2, accountId);
                ResultSet resultSet2 = getBalanceBeforeRequestedOperations.executeQuery();
                while (resultSet2.next()) {
                    balanceBeforeFirstOperationInTimeRange = resultSet2.getLong(1);
                }
            }

            List<Long> sumOfIncome = calculateSumOfIncome(operationsAmount);
            List<Long> saldo = calculateSaldo(operationsAmount, balanceBeforeFirstOperationInTimeRange);

            String path = "modules" + File.separatorChar + "module3" + File.separatorChar + "requested-statement.txt";
            File statemetsFile = new File(path);
            statemetsFile.createNewFile();
            createCsvTableAndWriteItTiFile(path, operationsId, operationsAmount, operationsTimestamp, sumOfIncome, saldo);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Long> calculateSumOfIncome(List<Long> operationsAmount) {
        List<Long> SumOfIncome = new ArrayList<>();
        Long sum = 0L;
        for (Long amount : operationsAmount) {
            if (amount > 0) sum += amount;
            SumOfIncome.add(sum);
        }
        return SumOfIncome;
    }

    private List<Long> calculateSaldo(List<Long> operationsAmount, long balance) {
        List<Long> saldo = new ArrayList<>();
        for (Long amount : operationsAmount) {
            balance += amount;
            saldo.add(balance);
        }
        return saldo;
    }
}