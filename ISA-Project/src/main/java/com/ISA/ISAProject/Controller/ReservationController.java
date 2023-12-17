package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.AvailableDateDto;
import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Dto.ReservationDto;
import com.ISA.ISAProject.Services.AvailableDateService;
import com.ISA.ISAProject.Services.CompanyAdminService;
import com.ISA.ISAProject.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

        @Autowired
        private ReservationService reservationService;

        @GetMapping("/getAll")
        public ResponseEntity<List<ReservationDto>> getAllReservations() {
            List<ReservationDto> allReservations = reservationService.getAllReservations();
            return new ResponseEntity<>(allReservations, HttpStatus.OK);
        }

        @GetMapping("/{reservationId}")
        public ResponseEntity<ReservationDto> getReservationById(@PathVariable Integer reservationId) {
            ReservationDto reservationDto = reservationService.getReservationById(reservationId);

            if (reservationDto != null) {
                return new ResponseEntity<>(reservationDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping("/new")
        public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
            // Call EquipmentService to create equipment
            ReservationDto createdReservation = reservationService.createReservation(reservationDto);

        if (createdReservation != null) {
            return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
