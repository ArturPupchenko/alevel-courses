package com.alevel.courses.module3.jdbc;

import com.alevel.courses.module3.jdbc.dao.AccountDao;
import com.alevel.courses.module3.jdbc.dao.OperationDao;
import com.alevel.courses.module3.jdbc.dao.UserDao;
import com.alevel.courses.module3.jdbc.model.Account;
import com.alevel.courses.module3.jdbc.model.Operation;
import com.alevel.courses.module3.jdbc.model.User;
import com.alevel.courses.module3.util.CsvTable;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StatementBuilderDao {
    private final Connection connection;
    private final OperationDao operationDao;
    private final UserDao userDao;
    private final AccountDao accountDao;
    private CsvTable csvTable;

    public StatementBuilderDao(Connection connection, OperationDao operationDao, UserDao userDao, AccountDao accountDao) {
        this.connection = connection;
        this.operationDao = operationDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    public CsvTable requestStatement(Long accountId, String from, String to) {
        Account account = accountDao.findAccountById(accountId);
        List<Operation> operations = operationDao.findOperationsByAccountBetweenDates(accountId, from, to);

        if (operations.size() == 0) {
            return null;
        }

        User user = userDao.findUserById(account.getUserId());

        Long sumOfIncomes = getSumOfIncome(from, to);

        csvTable = getCsvTable(account, user, operations, sumOfIncomes);

        return csvTable;
    }

    public void writeStatementToFile(String filePath) {
        if (csvTable != null) {
            CsvTable.writeIntoFile(csvTable, filePath);
        }
    }

    private Long getSumOfIncome(String from, String to) {
        Long sum = null;
        try (PreparedStatement incomesSum = connection.prepareStatement("select sum(amount) from operations where amount > 0 " +
                "and timestamp < ? and timestamp > ? and account_id = 1")) {
            incomesSum.setTimestamp(1, Timestamp.from(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(to).toInstant()));
            incomesSum.setTimestamp(2, Timestamp.from(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(from).toInstant()));

            ResultSet resultSet = incomesSum.executeQuery();
            if (resultSet.next())
                sum = resultSet.getLong(1);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sum;
    }

    private String checkOperationType(Long amount) {
        if (amount > 0)
            return "income";
        else if (amount < 0)
            return "expense";
        else
            throw new IllegalArgumentException("Not valid amount");
    }

    public CsvTable getCsvTable(Account account, User user, List<Operation> operations, Long sumOfIncomes) {
        List<String> headers = Arrays.asList("user name", "account id", "account name", "type", "amount","final balance", "sum of income", "time");
        String[][] cells = new String[operations.size()][headers.size()];

        int i = 0;
        int j = 0;
        for (Operation operation : operations) {
            String username = user.getName();
            String accountId = account.getId().toString();
            String accountName = account.getName();
            String type = checkOperationType(operation.getAmount());
            String amount = operation.getAmount().toString();
            String finalBalance = account.getBalance().toString();
            String incomesSum = sumOfIncomes.toString();
            String time = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format((operation.getTimestamp()));

            cells[i][j] = username;
            cells[i][j++] = accountId;
            cells[i][j++] = accountName;
            cells[i][j++] = type;
            cells[i][j++] = amount;
            cells[i][j++] = finalBalance;
            cells[i][j++] = incomesSum;
            cells[i][j++] = time;
            i++;
            j = 0;
        }
        return new CsvTable(headers, cells);
    }


}
