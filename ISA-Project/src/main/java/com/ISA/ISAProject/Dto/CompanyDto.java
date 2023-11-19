package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyDto {
    private String name;
    private String adress;
    private String description;
    private String grade;

    // Add default constructor
    public CompanyDto() {
    }

    public CompanyDto(Company company) {
        this.name = company.getName();
        this.adress = company.getAddress();
        this.description = company.getDescription();
        this.grade = company.getGrade();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String address){
        this.adress = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
