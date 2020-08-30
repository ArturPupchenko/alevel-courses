package com.alevel.courses.module3.jpahibernate.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("expense")
public class Expense extends Operation {

    @ManyToMany
    @JoinTable(
            name = "expense_categories_of_operations",
            joinColumns = @JoinColumn(name = "operation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private final Set<ExpenseCategory> categories = new HashSet<>();

    public Set<ExpenseCategory> getCategories() {
        return categories;
    }

    public Expense() {
    }

    public Expense(Account account, Long amount) {
        super(account, amount);
    }
}
