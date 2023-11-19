package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
}
