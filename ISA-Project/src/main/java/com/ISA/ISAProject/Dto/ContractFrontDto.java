package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.Contract;
import com.ISA.ISAProject.Model.ContractEquipment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContractFrontDto {

    private Integer id;
    private LocalDateTime exactDeliveryTime;
    private String hospitalName;
    private double hospitalAddressLong;

    private double hospitalAddressLat;

    private String companyName;

    private List<ContractEquipmentDto> contractsOfEquipment = new ArrayList<>();

    public ContractFrontDto(){
        contractsOfEquipment = new ArrayList<>();
    }

    public ContractFrontDto(Contract contract) {
        this.id = contract.getId();
        this.exactDeliveryTime = contract.getExactDeliveryTime();
        this.hospitalName = contract.getHospitalName();
        this.hospitalAddressLat = contract.getHospitalAddressLat();
        this.hospitalAddressLong = contract.getHospitalAddressLong();
        this.companyName = contract.getCompanyName();
        Set<ContractEquipment> equipmentSet = contract.getcontractsOfEquipment();
        List<ContractEquipmentDto> contractEquipmentDtoSet = new ArrayList<>();
        for (ContractEquipment conEq: equipmentSet) {
            ContractEquipmentDto contractEquipmentDto = new ContractEquipmentDto(conEq.getQuantity(), conEq.getEquipment().getName());
            contractEquipmentDtoSet.add(contractEquipmentDto);
        }
        this.contractsOfEquipment = contractEquipmentDtoSet;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ContractEquipmentDto> getContractsOfEquipment() {
        return contractsOfEquipment;
    }

    public void setContractsOfEquipment(List<ContractEquipmentDto> contractsOfEquipment) {
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

}
