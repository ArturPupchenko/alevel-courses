package com.alevel.courses.modules.module3.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@DiscriminatorValue("expense")
public class Expense extends Operation {

    @ManyToMany
    @JoinTable(
            name = "expense_categories_of_operations",
            joinColumns = @JoinColumn(name = "operation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private Collection<ExpenseCategory> expenseCategories = new ArrayList<>();


    public Expense(){
    }

    public Collection<ExpenseCategory> getExpenseCategories() {
        return expenseCategories;
    }

    public void setExpenseCategories(Collection<ExpenseCategory> expenseCategories) {
        this.expenseCategories = expenseCategories;
    }

    public void addExpenseCategory(ExpenseCategory expenseCategory) {
        if (!this.expenseCategories.contains(expenseCategory)) expenseCategories.add(expenseCategory);
    }
}