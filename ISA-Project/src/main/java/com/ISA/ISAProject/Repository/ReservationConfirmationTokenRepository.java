package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Token.ReservationConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationConfirmationTokenRepository extends JpaRepository<ReservationConfirmationToken,Long> {

    ReservationConfirmationToken findByReservationToken(String confirmationToken);
}
