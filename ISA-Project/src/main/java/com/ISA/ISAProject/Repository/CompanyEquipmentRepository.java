package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.CompanyEquipment;
import com.ISA.ISAProject.Model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyEquipmentRepository extends JpaRepository<CompanyEquipment, Integer> {
    @Modifying
    @Query("DELETE FROM CompanyEquipment ce WHERE ce.company.id = :companyId AND ce.equipment.id = :equipmentId")
    void deleteByCompanyIdAndEquipmentId(@Param("companyId") Integer companyId, @Param("equipmentId") Integer equipmentId);

    CompanyEquipment findByCompanyAndEquipment(Company company, Equipment equipment);

    @Modifying
    @Query("UPDATE CompanyEquipment ce SET ce.quantity = :newQuantity WHERE ce.company = :company AND ce.equipment = :equipment")
    void updateQuantity(@Param("company") Company company, @Param("equipment") Equipment equipment, @Param("newQuantity") Integer newQuantity);
}
