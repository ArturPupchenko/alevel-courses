package com.alevel.courses.modules.module3.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "expense_categories")
public class ExpenseCategory extends OperationCategory {

    @ManyToMany(mappedBy = "expenseCategories")
    private final Collection<Expense> operations = new ArrayList<>();

    public Collection<Expense> getOperations() {
        return operations;
    }

    public ExpenseCategory() {
    }

    public void addOpertaion(Expense expense) {
        if (!operations.contains(expense)) {
            this.operations.add(expense);
            expense.getExpenseCategories().add(this);
        }}
}

