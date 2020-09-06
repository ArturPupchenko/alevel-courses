package com.alevel.courses.modules.module3.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@DiscriminatorValue("income")
public class Income extends Operation {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "income_categories_of_operations",
            joinColumns = @JoinColumn(name = "operation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private Collection<IncomeCategory> incomeCategories = new ArrayList<>();


    public Income() {
    }

    public Collection<IncomeCategory> getIncomeCategories() {
        return incomeCategories;
    }

    public void setIncomeCategories(Collection<IncomeCategory> incomeCategories) {
        this.incomeCategories = incomeCategories;
    }

    public void addIncomeCategory(IncomeCategory incomeCategory) {
        if (!this.incomeCategories.contains(incomeCategory)) incomeCategories.add(incomeCategory);
    }

}