package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CompanyAdminRepository extends JpaRepository<CompanyAdmin, Integer> {
    CompanyAdmin findByUser_Email(String email);
    List<CompanyAdmin> findAllByCompany_Id(Integer companyId);
}


