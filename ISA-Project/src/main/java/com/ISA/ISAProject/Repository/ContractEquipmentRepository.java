package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.Contract;
import com.ISA.ISAProject.Model.ContractEquipment;
import com.ISA.ISAProject.Model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractEquipmentRepository extends JpaRepository<ContractEquipment,Integer> {
    ContractEquipment findContractEquipmentByContractAndEquipment(Contract contract, Equipment equipment);
}

