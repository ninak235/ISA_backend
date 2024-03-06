package com.ISA.ISAProject.Dto;

public class ReservationEquipmentDto {
    private String equipmentName;

    private Integer quantity;

    public ReservationEquipmentDto(Integer quantity, String equipmentName) {
        this.quantity = quantity;
        this.equipmentName = equipmentName;
    }

    public ReservationEquipmentDto(){

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
