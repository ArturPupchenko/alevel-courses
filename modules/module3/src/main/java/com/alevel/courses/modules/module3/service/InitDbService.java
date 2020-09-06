package com.alevel.courses.modules.module3.service;

import com.alevel.courses.modules.module3.dao.*;
import com.alevel.courses.modules.module3.entity.*;
import com.alevel.courses.modules.module3.entity.embeddable.PersonalInformation;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.Instant;

public class InitDbService {

    private final SessionFactory sessionFactory;

    private static Logger log = LoggerFactory.getLogger(InitDbService.class);

    public InitDbService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Instant[] initDb() {
        Instant[] instants = new Instant[2];


        OperationDao operationDao = new OperationDao(sessionFactory);
        UserDao userDao = new UserDao(sessionFactory);
        ExpenseCategoryDao expenseCategoryDao = new ExpenseCategoryDao(sessionFactory);
        IncomeCategoryDao incomeCategoryDao = new IncomeCategoryDao(sessionFactory);


        User user1 = new User();
        user1.setPersonalInformation(new PersonalInformation("John Wick", "Johnwich@gmail.com", "+34750455555"));
        Account account1 = new Account();
        Account account2 = new Account();
        user1.addAccount(account1);
        user1.addAccount(account2);
        userDao.saveOrUpdate(user1);

        User user2 = new User();
        user2.setPersonalInformation(new PersonalInformation("Ann White", "Annwhite@gmail.com", "+32470455555"));
        Account account3 = new Account();
        Account account4 = new Account();
        user2.addAccount(account3);
        user2.addAccount(account4);
        userDao.saveOrUpdate(user2);

        ExpenseCategory expenseCategory1 = new ExpenseCategory();
        expenseCategory1.setName("Food");
        expenseCategoryDao.saveOrUpdate(expenseCategory1);

        ExpenseCategory expenseCategory2 = new ExpenseCategory();
        expenseCategory2.setName("Transport");
        expenseCategoryDao.saveOrUpdate(expenseCategory2);

        IncomeCategory incomeCategory1 = new IncomeCategory();
        incomeCategory1.setName("Salary");
        incomeCategoryDao.saveOrUpdate(incomeCategory1);

        IncomeCategory incomeCategory2 = new IncomeCategory();
        incomeCategory2.setName("Gifts");
        incomeCategoryDao.saveOrUpdate(incomeCategory2);

        operationDao.addOperation(2, 1000, "Salary", "Gifts");
        operationDao.addOperation(3, -800, "Food", "Transport");
        operationDao.addOperation(4, 600, "Gifts");

        operationDao.addOperation(4, 1000, "Salary", "Gifts");
        operationDao.addOperation(3, -800, "Food", "Transport");
        operationDao.addOperation(2, 600, "Gifts");

        operationDao.addOperation(1, 1000, "Salary", "Gifts");
        operationDao.addOperation(1, -800, "Food", "Transport");
        operationDao.addOperation(1, 600, "Gifts");
        operationDao.addOperation(1, -200, "Transport");

        instants[0] = Instant.now(Clock.systemUTC());

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        operationDao.addOperation(1, 1000, "Salary", "Gifts");
        operationDao.addOperation(1, -800, "Food", "Transport");
        operationDao.addOperation(1, 600, "Gifts");
        operationDao.addOperation(1, -200, "Transport");

        operationDao.addOperation(2, -200, "Food");
        operationDao.addOperation(3, -300, "Transport");
        operationDao.addOperation(4, -350, "Transport");

        operationDao.addOperation(4, -100, "Food");
        operationDao.addOperation(3, 200, "Salary");
        operationDao.addOperation(2, 900, "Gifts");

        instants[1] = Instant.now(Clock.systemUTC());

        return instants;
    }
}




