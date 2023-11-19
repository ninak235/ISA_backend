package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    Company findCompanyByName(String companyName);
}
