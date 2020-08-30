package com.alevel.courses.module3;

import com.alevel.courses.module3.jdbc.StatementBuilderDao;
import com.alevel.courses.module3.jdbc.dao.AccountDao;
import com.alevel.courses.module3.jdbc.dao.OperationDao;
import com.alevel.courses.module3.jdbc.dao.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;


public class JDBCVersion {

    public static void run(String[] args) {
        String username = args[1];
        String password = args[2];

        Properties props = new Properties();
        String url = "jdbc:postgresql://localhost:5432/module2-db";
        props.setProperty("user", username);
        props.setProperty("password", password);

        try (Connection connection = DriverManager.getConnection(url, props)) {
            String dateTo = "2020-08-30T22:00:30";
            String dateFrom = "2020-08-30T23:40:30";

            Scanner in = new Scanner(System.in);
            System.out.println("Enter account id: ");
            Long id = in.nextLong();

            final OperationDao operationDao = new OperationDao(connection);
            final UserDao userDao = new UserDao(connection);
            final AccountDao accountDao = new AccountDao(connection);

            String path = "requested-statement.txt";
            final StatementBuilderDao statementBuilderDao = new StatementBuilderDao(connection, operationDao, userDao, accountDao);

            statementBuilderDao.requestStatement(id, dateFrom, dateTo);
            statementBuilderDao.writeStatementToFile(path);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}