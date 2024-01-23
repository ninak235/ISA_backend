package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailableDateRepository extends JpaRepository<AvailableDate,Integer> {

    List<AvailableDate> findAvailableDatesByAdmin_Company_Id(Integer companyId);

    List<AvailableDate> findAvailableDateByAdmin_Id(Integer adminId);

}
