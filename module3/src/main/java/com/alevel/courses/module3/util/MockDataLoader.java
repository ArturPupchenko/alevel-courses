package com.alevel.courses.module3.util;

import com.alevel.courses.module3.jpahibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class MockDataLoader {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MockDataLoader.class);

    public void init() {
        log.debug("Starting populating database.");

        User user1 = new User();
        user1.setName("John");

        Account account1 = new Account("Primary");
        account1.setBalance(0L);
        Account account2 = new Account("Secondary");
        account2.setBalance(0L);

        List<Account> accounts = Arrays.asList(account1, account2);
        user1.setAccounts(accounts);
        account1.setUser(user1);
        account2.setUser(user1);


        ExpenseCategory expenseCategory1 = new ExpenseCategory("Food");
        ExpenseCategory expenseCategory2 = new ExpenseCategory("Transport");
        ExpenseCategory expenseCategory3 = new ExpenseCategory("Communal payments");


        IncomeCategory incomeCategory1 = new IncomeCategory("Salary");
        IncomeCategory incomeCategory2 = new IncomeCategory("Premium");
        IncomeCategory incomeCategory3 = new IncomeCategory("Gifts");

        Configuration cfg = new Configuration().configure();

        try (SessionFactory sessionFactory = cfg.buildSessionFactory();
             Session session = sessionFactory.openSession()
        ) {

            try {
                session.beginTransaction();

                session.save(user1);
                session.save(expenseCategory1);
                session.save(expenseCategory2);
                session.save(expenseCategory3);
                session.save(incomeCategory1);
                session.save(incomeCategory2);
                session.save(incomeCategory3);

                session.getTransaction().commit();
                log.debug("Populating database has been finished.");
                log.debug("Added user: " + user1.toString());
                log.debug("Added accounts: " + user1.getAccounts().toString());
                log.debug("Added expenseCategories: " + Arrays.asList(expenseCategory1, expenseCategory2, expenseCategory3).toString());
                log.debug("Added incomeCategories: " + Arrays.asList(incomeCategory1, incomeCategory2, incomeCategory3).toString());

            } catch (Exception e) {
                log.debug("error when populating db with mock data", e);
                session.getTransaction().rollback();
            }
        }

    }

}
