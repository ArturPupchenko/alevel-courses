package com.alevel.courses.module3.jdbc.dao;

import com.alevel.courses.module3.jdbc.model.Income;
import com.alevel.courses.module3.jdbc.model.Operation;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationDao {
    private final Connection connection;

    public OperationDao(Connection connection) {
        this.connection = connection;
    }

    public List<Operation> findOperationsByAccountBetweenDates(Long accountId, String from, String to) {
        List<Operation> incomeOperations = new ArrayList<>();
        Operation operation;
        Long amount;
        Timestamp timestamp;
        Date parsedFrom = new Date();
        Date parsedTo = new Date();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            parsedFrom = dateFormat.parse(from);
            parsedTo = dateFormat.parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try (PreparedStatement getOperation = connection.prepareStatement("SELECT * from operations where timestamp < " +
                "? and timestamp > ? and account_id = ?")) {
            Timestamp timestampFrom = new java.sql.Timestamp(parsedFrom.getTime());
            Timestamp timestampTo = new java.sql.Timestamp(parsedTo.getTime());
            getOperation.setTimestamp(1, timestampTo);
            getOperation.setTimestamp(2, timestampFrom);
            getOperation.setLong(3, accountId);

            ResultSet resultSet = getOperation.executeQuery();

            while (resultSet.next()) {
                amount = resultSet.getLong(2);
                timestamp = resultSet.getTimestamp(3);

                operation = new Income();
                operation.setTimestamp(timestamp.toInstant());
                operation.setAmount(amount);
                operation.setAccountId(accountId);

                incomeOperations.add(operation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomeOperations;
    }
}
