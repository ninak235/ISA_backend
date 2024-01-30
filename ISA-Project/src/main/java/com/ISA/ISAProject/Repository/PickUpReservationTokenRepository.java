package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Token.PickUpReservationToken;
import com.ISA.ISAProject.Token.ReservationConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickUpReservationTokenRepository extends JpaRepository<PickUpReservationToken,Long> {
    PickUpReservationToken findByReservationToken(String confirmationToken);
}
