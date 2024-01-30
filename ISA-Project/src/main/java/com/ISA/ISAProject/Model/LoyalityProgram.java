package com.ISA.ISAProject.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "LoyalityProgram")
public class LoyalityProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NameCategory",nullable = false)
    private String nameCategory;

    @Column(name = "RequiredPoints")
    private long requiredPoints;

    @Column(name = "Discount")
    private long discount;

    @OneToMany(mappedBy = "loyalityProgram", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Customer> customers = new HashSet<>();


    public LoyalityProgram() {
        this.customers = new HashSet<>();
    }

    public LoyalityProgram(Integer id, String nameCategory, long requiredPoints, long discount, Set<Customer> customers) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.requiredPoints = requiredPoints;
        this.discount = discount;
        this.customers = customers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public long getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(long requiredPoints) {
        this.requiredPoints = requiredPoints;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
