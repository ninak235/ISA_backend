package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.CompanyEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyEquipmentRepository extends JpaRepository<CompanyEquipment, Integer> {
    @Modifying
    @Query("DELETE FROM CompanyEquipment ce WHERE ce.company.id = :companyId AND ce.equipment.id = :equipmentId")
    void deleteByCompanyIdAndEquipmentId(@Param("companyId") Integer companyId, @Param("equipmentId") Integer equipmentId);
}
