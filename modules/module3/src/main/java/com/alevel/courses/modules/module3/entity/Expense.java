package com.alevel.courses.modules.module3.entity;

import com.alevel.courses.modules.module3.util.DatesUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("expense")
public class Expense extends Operation {

    @ManyToMany
    @JoinTable(
            name = "expense_categories_of_operations",
            joinColumns = @JoinColumn(name = "operation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<ExpenseCategory> expenseCategories = new ArrayList<>();


    public Expense() {
    }

    public List<ExpenseCategory> getExpenseCategories() {
        return expenseCategories;
    }

    public void setExpenseCategories(List<ExpenseCategory> expenseCategories) {
        this.expenseCategories = expenseCategories;
    }

    public void addExpenseCategory(ExpenseCategory expenseCategory) {
        if (!this.expenseCategories.contains(expenseCategory)) expenseCategories.add(expenseCategory);
    }

    @Override
    public String toString() {
        String expenseAsString = ("Expense{" +
                "id = " + this.getId() +
                "; account id = " + this.getAccount().getId() +
                "; amount = " + this.getAmount() +
                "; creation time = " + DatesUtil.formatInstantToISO(this.getTimestamp()) +
                "; expense categories = {");
        for (int i = 0; i < expenseCategories.size() - 1; i++) {
            expenseAsString += expenseCategories.get(i).getName() + ",";
        }
        expenseAsString += expenseCategories.get(expenseCategories.size() - 1).getName();
        expenseAsString += '}';
        return expenseAsString;
    }
}
