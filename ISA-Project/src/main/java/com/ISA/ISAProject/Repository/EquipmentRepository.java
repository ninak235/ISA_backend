package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    Equipment findEquipmentByName(String equipmentName);
    Equipment findEquipmentById(Integer id);

}
