package com.ISA.ISAProject.Model;

import com.ISA.ISAProject.Enum.TypeOfEquipment;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Where(clause = "deleted = false")
@Entity(name = "Equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "EquipmentName" , nullable = false)
    private String name;

    @Column(name = "Description", nullable = false)
    private String description;
    @OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CompanyEquipment> companyEquipmentSet = new HashSet<>();

    /*
    @OneToMany(mappedBy = "equipment")
    private List<Reservation> reservations;
    */

    @Column(name = "deleted")
    private boolean deleted;

    @Enumerated(EnumType.STRING)
    @Column(name = "equipmentType",nullable = false)
    private TypeOfEquipment typeOfEquipment;

    @Column(name="grade", nullable = false)
    private String grade;

    @Column(name="price", nullable = false)
    private Float price;

    @ManyToMany(mappedBy = "reservationEquipments")
    private Set<Reservation> equipmentReservations = new HashSet<>();

    public Equipment(){
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


    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public TypeOfEquipment getTypeOfEquipment() {
        return typeOfEquipment;
    }

    public void setTypeOfEquipment(TypeOfEquipment typeOfEquipment) {
        this.typeOfEquipment = typeOfEquipment;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(id, equipment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deleted=" + deleted +
                ", typeOfEquipment=" + typeOfEquipment +
                ", grade='" + grade + '\'' +
                ", price=" + price +
                '}';
    }

    @Transient
    public Set<Company> getCompanyList() {
        Set<Company> companyList = new HashSet<>();
        for (CompanyEquipment companyEquipment : companyEquipmentSet) {
            companyList.add(companyEquipment.getCompany());
        }
        return companyList;
    }


    public void setCompanyEquipmentSet(Set<CompanyEquipment> companyEquipmentSet) {
        this.companyEquipmentSet = companyEquipmentSet;
    }

    public Set<Reservation> getEquipmentReservations() {
        return equipmentReservations;
    }

    public void setEquipmentReservations(Set<Reservation> equipmentReservations) {
        this.equipmentReservations = equipmentReservations;
    }
}
