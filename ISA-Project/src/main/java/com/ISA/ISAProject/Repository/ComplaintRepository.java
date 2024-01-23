package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {
    List<Complaint> findByCompanyAdminId(Integer companyAdminId);
}
