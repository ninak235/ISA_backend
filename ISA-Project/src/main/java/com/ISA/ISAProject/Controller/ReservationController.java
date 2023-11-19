package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.ReservationDto;
import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.Equipment;
import com.ISA.ISAProject.Model.Reservation;
import com.ISA.ISAProject.Services.CompanyAdminService;
import com.ISA.ISAProject.Services.CustomerService;
import com.ISA.ISAProject.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private CompanyAdminService companyAdminService;
    //private EquipmentService equipmentService;

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
    /*
    @PostMapping("/create")
    public ResponseEntity<ReservationDto> createReservation(@RequestParam Long adminId,
                                                         @RequestParam Long equipmentId,
                                                         @RequestParam LocalDateTime startTime,
                                                         @RequestParam Duration duration) {
        CompanyAdmin admin = companyAdminService.findById(adminId).orElseThrow(EntityNotFoundException::new);
        Equipment equipment = equipmentRepository.findById(equipmentId).orElseThrow(EntityNotFoundException::new);

        ReservationDto reservation = reservationService.createReservation(admin, equipment, startTime, duration);
        return ResponseEntity.ok(reservation);
    }


    /*
    @PostMapping("/confirm/{reservationId}")
    public ResponseEntity<String> confirmReservation(@PathVariable Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(EntityNotFoundException::new);
        reservationService.confirmReservation(reservation);
        return ResponseEntity.ok("Reservation confirmed");
    }
    */

}
