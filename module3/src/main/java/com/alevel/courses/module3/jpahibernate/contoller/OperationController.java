package com.alevel.courses.module3.jpahibernate.contoller;


import com.alevel.courses.module3.jpahibernate.dao.OperationDao;
import com.alevel.courses.module3.jpahibernate.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Set;

public class OperationController {

    private static final Logger log = LoggerFactory.getLogger(OperationController.class);

    private final OperationDao operationDao;

    public OperationController(OperationDao operationDao) {
        this.operationDao = operationDao;
    }

    public Income newIncomeOperation(Account account, Long amount, Set<IncomeCategory> categories) {
        Income operation = new Income();
        operation.setTimestamp(Instant.now());
        operation.setAmount(amount);
        operation.setAccount(account);
        operation.getCategories().addAll(categories);
        operationDao.saveOrUpdate(operation);
        return operation;

    }


    public Expense newExpenseOperation(Account account, Long amount, Set<ExpenseCategory> categories) {
        Expense operation = new Expense();
        operation.setTimestamp(Instant.now());
        operation.setAmount(amount);
        operation.setAccount(account);
        operation.getCategories().addAll(categories);
        operationDao.saveOrUpdate(operation);
        return operation;
    }


}
