package com.alevel.courses.modules.module3.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "income_categories")
public class IncomeCategory extends OperationCategory {

    @ManyToMany(mappedBy = "incomeCategories")
    private final Collection<Income> operations = new ArrayList<>();

    public Collection<Income> getOperations() {
        return operations;
    }

    public IncomeCategory() {
    }

    public void addOpertaion(Income income) {
        if (!operations.contains(income)) {
            this.operations.add(income);
            income.getIncomeCategories().add(this);
        }
    }
}