package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Enum.TypeOfEquipment;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Equipment;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EquipmentCompanyDto {
    private Integer id;
    private String name;
    private String description;
    private TypeOfEquipment typeOfEquipment;
    private String grade;
    private Float price;
    private List<CompanyDto> companySet;


    public EquipmentCompanyDto(){}

    public EquipmentCompanyDto(Equipment equipment){
        this.id = equipment.getId();
        this.name = equipment.getName();
        this.description = equipment.getDescription();
        this.typeOfEquipment = equipment.getTypeOfEquipment();
        this.grade = equipment.getGrade();
        this.price = equipment.getPrice();
        Hibernate.initialize(equipment.getCompanyList());

        this.companySet = new ArrayList<>(equipment.getCompanyList().stream()
                .map(CompanyDto::new)
                .collect(Collectors.toList()));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeOfEquipment getTypeOfEquipment(){ return typeOfEquipment; }

    public void setTypeOfEquipment(TypeOfEquipment typeOfEquipment) {
        this.typeOfEquipment = typeOfEquipment;
    }

    public String getGrade(){ return grade; }
    public void setGrade(String grade){ this.grade = grade; }

    public Float getPrice(){ return price; }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<CompanyDto> getCompanySet() {
        return companySet;
    }

    public void setCompanySet(List<CompanyDto> companySet) {
        this.companySet = companySet;
    }
}
