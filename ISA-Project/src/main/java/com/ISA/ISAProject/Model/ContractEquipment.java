package com.ISA.ISAProject.Model;

import javax.persistence.*;

@Entity
public class ContractEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="contract_id")
    Contract contract;

    @ManyToOne
    @JoinColumn(name="equipment_id")
    Equipment equipment;

    @Column(nullable = false)
    private Integer quantity;

    public ContractEquipment(Integer quantity) {
        this.quantity = quantity;
    }

    public ContractEquipment(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
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
