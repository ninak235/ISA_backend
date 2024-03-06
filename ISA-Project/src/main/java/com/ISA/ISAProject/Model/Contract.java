package com.ISA.ISAProject.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Contracts")
public class Contract implements Serializable {

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

    /*dobar primer alternative
    @ManyToMany(mappedBy = "contractsOfEquipment")
    private Set<Equipment> contractsOfEquipment;*/

    @OneToMany(mappedBy = "contract", fetch = FetchType.EAGER)
    Set<ContractEquipment> contractsOfEquipment;

    public Contract(){

    }

    public Contract(LocalDateTime exactDeliveryTime, String hospitalName, String companyName, double hospitalAddressLat, double hospitalAddressLong) {
        this.exactDeliveryTime = exactDeliveryTime;
        this.hospitalName = hospitalName;
        this.hospitalAddressLat = hospitalAddressLat;
        this.hospitalAddressLong = hospitalAddressLong;
        this.companyName = companyName;
        this.contractsOfEquipment = new HashSet<>();
    }

    public Set<ContractEquipment> getcontractsOfEquipment() {
        return contractsOfEquipment;
    }

    public void setcontractsOfEquipment(Set<ContractEquipment> contractsOfEquipment) {
        this.contractsOfEquipment = contractsOfEquipment;
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
                "contractsOfEquipment=" + contractsOfEquipment +
                ", exactDeliveryTime=" + exactDeliveryTime +
                ", hospitalName='" + hospitalName + '\'' +
                ", hospitalAddressLong=" + hospitalAddressLong +
                ", hospitalAddressLat=" + hospitalAddressLat +
                ", companyName='" + companyName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
