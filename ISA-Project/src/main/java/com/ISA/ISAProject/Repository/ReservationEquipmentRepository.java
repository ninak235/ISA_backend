package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.ReservationEquipment;
import com.ISA.ISAProject.Token.ReservationConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationEquipmentRepository extends JpaRepository<ReservationEquipment,Integer> {

}

