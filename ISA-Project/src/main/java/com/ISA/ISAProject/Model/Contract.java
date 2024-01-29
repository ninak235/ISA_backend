package com.ISA.ISAProject.Model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ExactDeliveryTime",nullable = false)
    private LocalDateTime exactDeliveryTime;
    @Column(name = "HospitalName",nullable = false)
    private String hospitalName;
    @Column(name = "HospitalAddressLong",nullable = false)
    private double hospitalAddressLong;
    @Column(name = "HospitalAddressLat",nullable = false)
    private double hospitalAddressLat;
    @Column(name = "CompanyName",nullable = false)
    private String companyName;
    @Column(name= "Quantity",nullable = false)
    private double quantity;
    @ManyToMany(mappedBy = "contractsOfEquipment")
    private Set<Equipment> equipmentNames;

    public Contract(){

    }

    public Contract(LocalDateTime exactDeliveryTime, String hospitalName, String companyName, double hospitalAddressLat, double hospitalAddressLong, double quantity) {
        this.exactDeliveryTime = exactDeliveryTime;
        this.hospitalName = hospitalName;
        this.hospitalAddressLat = hospitalAddressLat;
        this.hospitalAddressLong = hospitalAddressLong;
        this.companyName = companyName;
        this.equipmentNames = new HashSet<>();
        this.quantity = quantity;
    }

    public Set<Equipment> getEquipmentNames() {
        return equipmentNames;
    }

    public void setEquipmentNames(Set<Equipment> equipmentNames) {
        this.equipmentNames = equipmentNames;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getExactDeliveryTime() {
        return exactDeliveryTime;
    }

    public void setExactDeliveryTime(LocalDateTime exactDeliveryTime) {
        this.exactDeliveryTime = exactDeliveryTime;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public double getHospitalAddressLong() {
        return hospitalAddressLong;
    }

    public void setHospitalAddressLong(double hospitalAddressLong) {
        this.hospitalAddressLong = hospitalAddressLong;
    }

    public double getHospitalAddressLat() {
        return hospitalAddressLat;
    }

    public void setHospitalAddressLat(double hospitalAddressLat) {
        this.hospitalAddressLat = hospitalAddressLat;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "equipmentNames=" + equipmentNames +
                ", quantity=" + quantity +
                ", exactDeliveryTime=" + exactDeliveryTime +
                ", hospitalName='" + hospitalName + '\'' +
                ", hospitalAddressLong=" + hospitalAddressLong +
                ", hospitalAddressLat=" + hospitalAddressLat +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
