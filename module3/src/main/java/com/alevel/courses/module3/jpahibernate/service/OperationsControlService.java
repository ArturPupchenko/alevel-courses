package com.alevel.courses.module3.jpahibernate.service;


import com.alevel.courses.module3.jpahibernate.contoller.AccountController;
import com.alevel.courses.module3.jpahibernate.contoller.CategoryController;
import com.alevel.courses.module3.jpahibernate.contoller.OperationController;
import com.alevel.courses.module3.jpahibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class OperationsControlService {

    private static final Logger log = LoggerFactory.getLogger(OperationsControlService.class);

    private final SessionFactory sessionFactory;
    private final OperationController operationController;
    private final CategoryController categoryController;
    private final AccountController accountController;
    private Session session;
    private String[] categories;
    private Account account;
    private Long amount;
    private Long userId;


    public OperationsControlService(SessionFactory sessionFactory, OperationController operationController,
                                    CategoryController categoryController, AccountController accountController) {
        this.sessionFactory = sessionFactory;
        this.operationController = operationController;
        this.categoryController = categoryController;
        this.accountController = accountController;
        ;
    }

    public void createOperation(Long userId) throws Exception {
        session = sessionFactory.getCurrentSession();
        this.userId = userId;

        session.beginTransaction();
        requestData();
        if (amount > 0) {
            makeIncomeOperation();
            log.info("User " + account.getUser().getName() + " with account " + account.getName() +
                    " made income operation with amount " + amount + " and categories " + Arrays.toString(categories));
        } else {
            makeExpenseOperation();
            log.info("Username " + account.getUser().getName() + " with account id " + account.getName() +
                    " made expense operation with amount " + amount + " and categories " + Arrays.toString(categories));
        }
        session.getTransaction().commit();
    }

    private void requestData() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter account name: ");
        String accountName = in.nextLine();
        account = session.bySimpleNaturalId(Account.class).load(accountName);

        System.out.println("Enter amount: ");
        amount = in.nextLong();

        System.out.println("Enter operation`s categories (separated with commas): ");
        String categoriesString = in.next();
        categories = categoriesString.trim().split(",");
    }

    private void makeIncomeOperation() {
        Set<IncomeCategory> incomeCategories = new HashSet<>();
        IncomeCategory incomeCategory;

        for (String name : categories) {

            incomeCategory = categoryController.findIncomeCategoryByName(name);

            if (incomeCategory == null) {
                incomeCategory = categoryController.newIncomeCategory(name);
            }
            incomeCategories.add(incomeCategory);

        }
        accountController.updateBalance(amount, account.getId());
        Income operation = operationController.newIncomeOperation(account, amount, incomeCategories);

        for (IncomeCategory category : incomeCategories) {
            category.getOperations().add(operation);
            categoryController.updateIncomeCategory(category);
        }
    }

    private void makeExpenseOperation() {
        Set<ExpenseCategory> expenseCategories = new HashSet<>();
        ExpenseCategory expenseCategory;

        for (String name : categories) {

            expenseCategory = categoryController.findExpenseCategoryByName(name);

            if (expenseCategory == null) {
                expenseCategory = categoryController.newExpenseCategory(name);
            }
            expenseCategories.add(expenseCategory);

        }
        accountController.updateBalance(amount, account.getId());
        Expense operation = operationController.newExpenseOperation(account, amount, expenseCategories);

        for (ExpenseCategory category : expenseCategories) {
            category.getOperations().add(operation);
            categoryController.updateExpenseCategory(category);
        }

    }
}
