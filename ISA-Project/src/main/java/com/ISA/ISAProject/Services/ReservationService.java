package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.ComEqDto;
import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Dto.ReservationDto;
import com.ISA.ISAProject.Mapper.EquipmentMapper;
import com.ISA.ISAProject.Mapper.ReservationMapper;
import com.ISA.ISAProject.Model.*;
import com.ISA.ISAProject.Repository.CompanyEquipmentRepository;
import com.ISA.ISAProject.Repository.EquipmentRepository;
import com.ISA.ISAProject.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CompanyAdminService companyAdminService;


    @Transactional
    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.mapReservationsToDto(reservations);
    }

    @Transactional
    public List<ReservationDto> getAllReservationsByCompanyAdminId(Integer companyAdminId) {
        List<Reservation> reservations = reservationRepository.findByCompanyAdminId(companyAdminId);
        return reservationMapper.mapReservationsToDto(reservations);
    }

    @Transactional
    public ReservationDto getReservationById(Integer reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        return optionalReservation.map(reservationMapper::mapReservationToDto).orElse(null);
    }

    @Transactional
    public List<ReservationDto> getReservationsByUserId(Integer userId) {
        List<Reservation> userReservations = reservationRepository.findAllByCustomer_Id(userId);
        return reservationMapper.mapReservationsToDto(userReservations);
    }

    @Transactional
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Customer customer = customerService.getById(reservationDto.getCustomerId());

        CompanyAdmin companyAdmin = companyAdminService.getById(reservationDto.getCompanyAdminId());
        Reservation reservation = reservationMapper.mapDtoToEntity(reservationDto);

        reservation.setCompanyAdmin(companyAdmin);
        reservation.setCustomer(customer);

        //Reservation reservation1 = new Reservation(reservation);
        Reservation reservation1 = reservationRepository.save(reservation);
        return new ReservationDto(reservation1);
    }

    public void updateReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }
}
