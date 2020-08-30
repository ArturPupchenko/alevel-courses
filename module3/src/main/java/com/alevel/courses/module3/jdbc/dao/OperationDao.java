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

        try (PreparedStatement getOperation = connection.prepareStatement("SELECT * from operations where timestamp < " +
                "? and timestamp > ? and account_id = ?")) {

            getOperation.setTimestamp(1, Timestamp.from(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(to).toInstant()));
            getOperation.setTimestamp(2, Timestamp.from(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(from).toInstant()));
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return incomeOperations;
    }
}
