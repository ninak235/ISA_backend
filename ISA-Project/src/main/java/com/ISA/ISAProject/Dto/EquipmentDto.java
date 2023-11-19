package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Enum.EquipmentStatus;
import com.ISA.ISAProject.Model.Equipment;

public class EquipmentDto {
    private Integer id;
    private String name;
    private String description;
    private EquipmentStatus status;
    public EquipmentDto(){}

    public EquipmentDto(Equipment equipment){
        this.id = equipment.getId();
        this.name = equipment.getName();
        this.description = equipment.getDescription();
        this.status = equipment.getStatus();
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

    public EquipmentStatus getStatus() {
        return status;
    }

    public void setStatus(EquipmentStatus status) {
        this.status = status;
    }
}
