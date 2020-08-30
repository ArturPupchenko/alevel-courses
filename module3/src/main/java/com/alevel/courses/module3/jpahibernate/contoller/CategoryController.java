package com.alevel.courses.module3.jpahibernate.contoller;


import com.alevel.courses.module3.jpahibernate.dao.ExpenseCategoryDao;
import com.alevel.courses.module3.jpahibernate.dao.IncomeCategoryDao;
import com.alevel.courses.module3.jpahibernate.entity.ExpenseCategory;
import com.alevel.courses.module3.jpahibernate.entity.IncomeCategory;

public class CategoryController{
    private final ExpenseCategoryDao expenseCategoryDao;
    private final IncomeCategoryDao incomeCategoryDao;

    public CategoryController(ExpenseCategoryDao expenseCategoryDao, IncomeCategoryDao incomeCategoryDao) {
        this.expenseCategoryDao = expenseCategoryDao;
        this.incomeCategoryDao = incomeCategoryDao;
    }

    public void updateExpenseCategory(ExpenseCategory operationCategory) {
        expenseCategoryDao.saveOrUpdate(operationCategory);
    }

    public void updateIncomeCategory(IncomeCategory operationCategory) {
        incomeCategoryDao.saveOrUpdate(operationCategory);
    }

    public ExpenseCategory newExpenseCategory(String name) {
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setName(name);
        expenseCategoryDao.saveOrUpdate(expenseCategory);
        return expenseCategory;
    }

    public IncomeCategory newIncomeCategory(String name) {
        IncomeCategory incomeCategory = new IncomeCategory();
        incomeCategory.setName(name);
        incomeCategoryDao.saveOrUpdate(incomeCategory);
        return incomeCategory;
    }

    public IncomeCategory findIncomeCategoryByName(String name) {
        IncomeCategory incomeCategory = incomeCategoryDao.findByName(name);
        return incomeCategory;
    }

    public ExpenseCategory findExpenseCategoryByName(String name) {
        ExpenseCategory expenseCategory = expenseCategoryDao.findByName(name);
        return expenseCategory;
    }
}
