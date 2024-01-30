package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.LoyalityProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyalityProgramRepository extends JpaRepository<LoyalityProgram,Integer> {
}
