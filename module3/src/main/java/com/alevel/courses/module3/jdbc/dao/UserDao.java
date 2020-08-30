package com.alevel.courses.module3.jdbc.dao;

import com.alevel.courses.module3.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User findUserById(Long id) {
        User user = new User();
        try (PreparedStatement getOperation = connection.prepareStatement("SELECT * from users where id = ?")) {
            getOperation.setLong(1, id);

            String name;

            ResultSet resultSet = getOperation.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString(1);
                user.setName(name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
