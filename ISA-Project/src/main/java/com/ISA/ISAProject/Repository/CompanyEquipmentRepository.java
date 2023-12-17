package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.CompanyEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyEquipmentRepository extends JpaRepository<CompanyEquipment, Integer> {
}

