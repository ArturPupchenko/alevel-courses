package com.alevel.courses.module3.jdbc.dao;

import com.alevel.courses.module3.jdbc.model.Account;
import org.hibernate.boot.model.naming.IllegalIdentifierException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {
    private final Connection connection;

    public AccountDao(Connection connection) {
        this.connection = connection;
    }

    public Account findAccountById(Long id) {
        Account account = new Account();
        try (PreparedStatement getOperation = connection.prepareStatement("SELECT * from accounts where id = ?")) {
            getOperation.setLong(1, id);
            Long accountId;
            String name;
            Long userId;
            Long balance;

            ResultSet resultSet = getOperation.executeQuery();
            if (resultSet.next()) {
                accountId = resultSet.getLong(1);
                name = resultSet.getString(3);
                balance = resultSet.getLong(2);
                userId = resultSet.getLong(4);
                account.setId(accountId);
                account.setName(name);
                account.setBalance(balance);
                account.setUserId(userId);
            } else
                throw new IllegalIdentifierException("Account with id " + id + " was not found");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
