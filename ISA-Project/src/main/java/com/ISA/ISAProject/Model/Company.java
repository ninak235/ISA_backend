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

    @ManyToMany (cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "CompanyEquipment", joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "id"))
    private Set<Equipment> equipmentSet = new HashSet<>();

    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<CompanyAdmin> companyAdminSet = new HashSet<>();

    @Column(name = "deleted")
    private boolean deleted;

    public Company(){

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

    public String getAdress() {
        return adress;
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

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", description='" + description + '\'' +
                ", grade='" + grade + '\'' +
                ", equipmentSet=" + equipmentSet +
                ", companyAdminSet=" + companyAdminSet +
                '}';
    }
}
