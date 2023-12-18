package com.ISA.ISAProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Where(clause = "deleted = false")
@Entity(name = "Company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name" , unique = true,nullable = false)
    private String name;

    @Column(name = "Adress" , unique = true,nullable = false)
    private String adress;

    @Column(name = "Description",nullable = false)
    private String description;

    //Slika mozda
    @Column(name = "Grade")
    private String grade;

    @Column(name = "StartWorkingTime")
    private LocalTime startWorkingTime;

    @Column(name = "EndWorkingTime")
    private LocalTime endWorkingTime;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CompanyEquipment> companyEquipmentSet = new HashSet<>();

    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<CompanyAdmin> companyAdminSet = new HashSet<>();

    @Column(name = "deleted")
    private boolean deleted;

    public Company(){
        this.companyEquipmentSet = new HashSet<>();
        this.deleted=false;
        this.companyAdminSet = new HashSet<>();
    }

    public Company(String name, String adress, String description, String grade, LocalTime startWorkingTime, LocalTime endWorkingTime, Set<CompanyEquipment> companyEquipmentSet, Set<CompanyAdmin> companyAdminSet) {
        this.name = name;
        this.adress = adress;
        this.description = description;
        this.grade =  grade;
        this.startWorkingTime = startWorkingTime;
        this.endWorkingTime = endWorkingTime;
        this.companyEquipmentSet = companyEquipmentSet;
        this.companyAdminSet = companyAdminSet;
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

    public String getAddress() {
        return  adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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


    @Transient
    public Set<Equipment> getEquipmentList() {
        Set<Equipment> equipmentList = new HashSet<>();
        for (CompanyEquipment companyEquipment : companyEquipmentSet) {
            equipmentList.add(companyEquipment.getEquipment());
        }
        return equipmentList;
    }

    public void setEquipment(Set<CompanyEquipment> equipment) {
        this.companyEquipmentSet = equipment;
    }

    public Set<CompanyAdmin> getCompanyAdmin() {
        return companyAdminSet;
    }

    public void setCompanyAdmin(Set<CompanyAdmin> companyAdmin) {
        this.companyAdminSet = companyAdmin;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void addCompanyAdmin(CompanyAdmin companyAdmin){
        companyAdminSet.add(companyAdmin);
        companyAdmin.setCompany(this);
    }

    public void removeCompanyAdmin(CompanyAdmin admin) {
        companyAdminSet.remove(admin);
        admin.setCompany(null);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public LocalTime getStartWorkingTime() {
        return startWorkingTime;
    }

    public void setStartWorkingTime(LocalTime startWorkingTime) {
        this.startWorkingTime = startWorkingTime;
    }

    public LocalTime getEndWorkingTime() {
        return endWorkingTime;
    }

    public void setEndWorkingTime(LocalTime endWorkingTime) {
        this.endWorkingTime = endWorkingTime;
    }
}
