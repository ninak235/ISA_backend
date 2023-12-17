package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.CompanyAdminDto;
import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Dto.ReservationDto;
import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.Equipment;
import com.ISA.ISAProject.Model.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {
    private final ModelMapper modelMapper;


    @Autowired
    public ReservationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ReservationDto mapReservationToDto(Reservation reservation) {
        ReservationDto reservationDto = modelMapper.map(reservation, ReservationDto.class);
        reservationDto.setCustomerName(reservation.getCustomer().getUser().getFirstName());
        reservationDto.setCustomerLastName(reservation.getCustomer().getUser().getLastName());
        return reservationDto;
    }


    public List<ReservationDto> mapReservationsToDto(List<Reservation> reservations) {
        return reservations.stream()
                .map(this::mapReservationToDto)
                .collect(Collectors.toList());
    }

    public Reservation mapDtoToEntity(ReservationDto reservationDto) {
        return modelMapper.map(reservationDto, Reservation.class);
    }
}
