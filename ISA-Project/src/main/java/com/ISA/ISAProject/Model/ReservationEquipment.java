package com.ISA.ISAProject.Model;

import javax.persistence.*;
@Entity

public class ReservationEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="reservation_id")
    Reservation reservation;

    @ManyToOne
    @JoinColumn(name="equipment_id")
    Equipment equipment;

    @Column(nullable = false)
    private Integer quantity;

    public ReservationEquipment(Integer quantity) {
        this.quantity = quantity;
    }

    public ReservationEquipment(){

    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
