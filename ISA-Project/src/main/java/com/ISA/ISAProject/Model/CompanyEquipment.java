package com.ISA.ISAProject.Model;

import io.swagger.models.auth.In;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CompanyEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToMany(mappedBy = "companyEquipments")
    private Set<Reservation> reservations = new HashSet<>();

    public CompanyEquipment(Integer id, Company company, Equipment equipment, Integer quantity) {
        this.id = id;
        this.company = company;
        this.equipment = equipment;
        this.quantity = quantity;
    }

    public CompanyEquipment() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

