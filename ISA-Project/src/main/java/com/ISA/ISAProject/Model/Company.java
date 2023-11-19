package com.ISA.ISAProject.Model;

import org.hibernate.annotations.Where;
import javax.persistence.*;
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

    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Equipment> equipmentSet = new HashSet<>();

    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<CompanyAdmin> companyAdminSet = new HashSet<>();

    @Column(name = "deleted")
    private boolean deleted;

    public Company(){
        this.equipmentSet = new HashSet<>();
        this.deleted=false;
        this.companyAdminSet = new HashSet<>();
    }

    public Company(String name, String adress, String description, String grade, Set<Equipment> equipmentSet, Set<CompanyAdmin> companyAdminSet) {
        this.name = name;
        this.adress = adress;
        this.description = description;
        this.grade =  grade;
        this.equipmentSet = equipmentSet;
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

    public Set<Equipment> getEquipment() {
        return equipmentSet;
    }

    public void setEquipment(Set<Equipment> equipment) {
        this.equipmentSet = equipment;
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

    public void addEquipment(Equipment equipment) {
        equipmentSet.add(equipment);
        equipment.setCompany(this);
    }

    public void removeEquipment(Equipment equipment){
        equipmentSet.remove(equipment);
        equipment.setCompany(null);
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



}
