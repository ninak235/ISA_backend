package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Equipment;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ComEqDto {
    private Integer id;
    private Integer companyId;

    private Integer equipmentId;

    private Integer quantity;

    public ComEqDto(Integer id, Integer companyId, Integer equipmentId, Integer quantity) {
        this.id = id;
        this.companyId = companyId;
        this.equipmentId = equipmentId;
        this.quantity = quantity;
    }

    public ComEqDto(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
