package com.alevel.courses.modules.module3.entity;

import com.alevel.courses.modules.module3.util.DatesUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@DiscriminatorValue("income")
public class Income extends Operation {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "income_categories_of_operations",
            joinColumns = @JoinColumn(name = "operation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<IncomeCategory> incomeCategories = new ArrayList<>();


    public Income() {
    }

    public List<IncomeCategory> getIncomeCategories() {
        return incomeCategories;
    }

    public void setIncomeCategories(List<IncomeCategory> incomeCategories) {
        this.incomeCategories = incomeCategories;
    }

    public void addIncomeCategory(IncomeCategory incomeCategory) {
        if (!this.incomeCategories.contains(incomeCategory)) incomeCategories.add(incomeCategory);
    }

    @Override
    public String toString() {
        String incomeAsString = ("Income{" +
                "id = " + this.getId() +
                "; account id = " + this.getAccount().getId() +
                "; amount = " + this.getAmount() +
                "; creation time = " + DatesUtil.formatInstantToISO(this.getTimestamp()) +
                "; income categories = {");
        for (int i = 0; i < incomeCategories.size() - 1; i++) {
            incomeAsString += incomeCategories.get(i).getName() + ",";
        }
        incomeAsString += incomeCategories.get(incomeCategories.size() - 1).getName();
        incomeAsString += '}';
        return incomeAsString;
    }
}