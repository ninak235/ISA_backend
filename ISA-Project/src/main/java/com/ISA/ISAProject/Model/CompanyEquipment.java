package com.ISA.ISAProject.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CompanyEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToMany(mappedBy = "companyEquipments")
    private Set<Reservation> reservations = new HashSet<>();

    public CompanyEquipment(Long id, Company company, Equipment equipment, Integer quantity) {
        this.id = id;
        this.company = company;
        this.equipment = equipment;
        this.quantity = quantity;
    }

    public CompanyEquipment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

