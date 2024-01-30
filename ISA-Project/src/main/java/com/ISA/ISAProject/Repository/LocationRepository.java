package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.Complaint;
import com.ISA.ISAProject.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Integer> {
}
