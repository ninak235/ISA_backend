package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Enum.ReservationStatus;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Model.LoyalityProgram;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoyalityProgramDto {
    private Integer id;

    private String nameCategory;

    private long requiredPoints;

    private long discount;

    public LoyalityProgramDto() {
    }

    public LoyalityProgramDto(LoyalityProgram loyalityProgram) {
        this.id = loyalityProgram.getId();
        this.nameCategory = loyalityProgram.getNameCategory();
        this.requiredPoints = loyalityProgram.getRequiredPoints();
        this.discount = loyalityProgram.getDiscount();
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
}
