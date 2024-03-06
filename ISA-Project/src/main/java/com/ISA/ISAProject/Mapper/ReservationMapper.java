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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation res: reservations) {
            ReservationDto resDto = new ReservationDto(res);
            reservationDtos.add(resDto);
        }
        return  reservationDtos;
    }

    public Reservation mapDtoToEntity(ReservationDto reservationDto) {
        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);

        return reservation;
    }


}
