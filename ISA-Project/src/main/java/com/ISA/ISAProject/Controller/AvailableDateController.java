package com.ISA.ISAProject.Controller;


import com.ISA.ISAProject.Services.CompanyAdminService;
import com.ISA.ISAProject.Dto.AvailableDateDto;
import com.ISA.ISAProject.Services.AvailableDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class AvailableDateController {

    @Autowired
    private AvailableDateService availableDateService;
    @Autowired
    private CompanyAdminService companyAdminService;
    //private EquipmentService equipmentService;

    @GetMapping("/getAll")
    public ResponseEntity<List<AvailableDateDto>> getAllAvailableDates() {
        List<AvailableDateDto> allAvailableDates = availableDateService.getAllReservations();
        return new ResponseEntity<>(allAvailableDates, HttpStatus.OK);
    }

    @GetMapping("/{availableDateId}")
    public ResponseEntity<AvailableDateDto> getAvailableDateById(@PathVariable Integer availableDateId) {
        AvailableDateDto availableDateDto = availableDateService.getReservationById(availableDateId);

        if (availableDateDto != null) {
            return new ResponseEntity<>(availableDateDto, HttpStatus.OK);
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
