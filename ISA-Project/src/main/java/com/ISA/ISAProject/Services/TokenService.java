package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Repository.AccountConfirmationTokenRepository;
import com.ISA.ISAProject.Repository.PickUpReservationTokenRepository;
import com.ISA.ISAProject.Repository.ReservationConfirmationTokenRepository;
import com.ISA.ISAProject.Token.AccountConfirmationToken;
import com.ISA.ISAProject.Token.PickUpReservationToken;
import com.ISA.ISAProject.Token.ReservationConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private AccountConfirmationTokenRepository _confirmationTokenRepository;
    @Autowired
    private ReservationConfirmationTokenRepository _reservationTokenRepository;
    @Autowired
    private PickUpReservationTokenRepository _pickUpReservationTokenRepository;

    public AccountConfirmationToken getConfirmationToken(String confrimationToken){
        return _confirmationTokenRepository.findByConfirmationToken(confrimationToken);
    }

    public ReservationConfirmationToken getReservationToken(String reservationToken){
        return _reservationTokenRepository.findByReservationToken(reservationToken);
    }

    public PickUpReservationToken getPickUpReservationToken(String reservationToken){
        return _pickUpReservationTokenRepository.findByReservationToken(reservationToken);
    }
}
