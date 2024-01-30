package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.Contract;
import com.ISA.ISAProject.Model.Equipment;

import javax.persistence.*;

public class ContractEquipmentDto {
    private String equipmentName;

    private Integer quantity;

    public ContractEquipmentDto(Integer quantity, String equipmentName) {
        this.quantity = quantity;
        this.equipmentName = equipmentName;
    }

    public ContractEquipmentDto(){

    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
}
