package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.ReservationDto;
import com.ISA.ISAProject.Mapper.ReservationMapper;
import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.Equipment;
import com.ISA.ISAProject.Model.Reservation;
import com.ISA.ISAProject.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Transactional
    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.mapReservationsToDto(reservations);
    }

    @Transactional
    public ReservationDto getReservationById(Integer reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        return optionalReservation.map(reservationMapper::mapReservationToDto).orElse(null);
    }

    @Transactional
    public ReservationDto createReservation(CompanyAdmin admin, Equipment equipment, LocalDateTime startTime, Duration duration) {
        // Perform validation and business logic as needed
        // You may want to check for equipment availability, admin privileges, etc.

        Reservation reservation = new Reservation();
        reservation.setAdmin(admin);
        reservation.setEquipment(equipment);
        reservation.setStartTime(startTime);
        reservation.setDuration(duration);
        reservation.setAdminConfirmationTime(null);
        reservation.setConfirmed(false);
        // Set properties based on request and business logic
        // reservation.setAdmin(...);
        // reservation.setEquipment(...);
        // reservation.setStartTime(...);
        // reservation.setDuration(...);

        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.mapReservationToDto(savedReservation);
    }

    // Additional methods for updating, confirming, and other reservation-related actions
}
