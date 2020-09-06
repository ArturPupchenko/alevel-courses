package com.alevel.courses.modules.module3.dao;

import com.alevel.courses.modules.module3.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class OperationDao {


    private final SessionFactory sessionFactory;

    public OperationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void addOperation(long accountId, long amount, String... categoriesNames) {
        if (categoriesNames.length > 0) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                Account account = session.get(Account.class, accountId);
                Long balance = account.getBalance();

                if (amount > 0) {
                    Income income = new Income();
                    income.setAccount(account);
                    income.setAmount(amount);
                    for (int i = 0; i < categoriesNames.length; i++) {
                        IncomeCategory currentIncomeCategory = session.bySimpleNaturalId(IncomeCategory.class).load(categoriesNames[i]);
                        income.addIncomeCategory(currentIncomeCategory);
                    }
                    account.setBalance(account.getBalance() + amount);

                    transaction = session.beginTransaction();
                    session.saveOrUpdate(account);
                    session.saveOrUpdate(income);
                    transaction.commit();

                } else if (balance > amount) {

                    Expense expense = new Expense();
                    expense.setAccount(account);
                    expense.setAmount(amount);
                    for (int i = 0; i < categoriesNames.length; i++) {
                        ExpenseCategory currentExpenseCategory = session.bySimpleNaturalId(ExpenseCategory.class).load(categoriesNames[i]);
                        expense.addExpenseCategory(currentExpenseCategory);
                    }
                    account.setBalance(account.getBalance() + amount);

                    transaction = session.beginTransaction();
                    session.saveOrUpdate(account);
                    session.saveOrUpdate(expense);
                    transaction.commit();
                }

            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                    throw e;
                }
            }
        }
    }
}

