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
    @Autowired
    private EquipmentRepository equipmentRepository;

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
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Customer customer = customerService.getById(reservationDto.getCustomerId());
        CompanyAdmin companyAdmin = companyAdminService.getById(reservationDto.getCompanyAdminId());
        System.out.println("---------1------------------: "+ reservationDto.getReservationEquipments().size());
        Reservation reservation = reservationMapper.mapDtoToEntity(reservationDto);


        System.out.println("---------2------------------: "+ reservation.getReservationEquipments().size());

        reservation.setCompanyAdmin(companyAdmin);
        reservation.setCustomer(customer);

        Reservation reservation1 = new Reservation(reservation);
        reservationRepository.save(reservation1);
        return new ReservationDto(reservation1);
    }
}
