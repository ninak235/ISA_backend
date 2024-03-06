package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.AvailableDateDto;
import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Dto.ReservationCancelationDTO;
import com.ISA.ISAProject.Dto.ReservationDto;
import com.ISA.ISAProject.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

        @Autowired
        private ReservationService reservationService;
        @Autowired
        private EmailService emailService;
        @Autowired
        private UserService userService;

    @GetMapping("/getAll")
    //@PreAuthorize("hasRole('COMPANYADMIN')")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> allReservations = reservationService.getAllReservations();
        return new ResponseEntity<>(allReservations, HttpStatus.OK);
    }

    @GetMapping("/getAllByAdminId/{companyAdminId}")
    //@PreAuthorize("hasRole('COMPANYADMIN')")
    public ResponseEntity<List<ReservationDto>> getAllReservationsByCompanyAdminId(@PathVariable Integer companyAdminId) {
        List<ReservationDto> allReservations = reservationService.getAllReservationsByCompanyAdminId(companyAdminId);
        return new ResponseEntity<>(allReservations, HttpStatus.OK);
    }

    @GetMapping("/{reservationId}")
    //@PreAuthorize("hasRole('COMPANYADMIN')")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Integer reservationId) {
        ReservationDto reservationDto = reservationService.getReservationById(reservationId);

            if (reservationDto != null) {
                return new ResponseEntity<>(reservationDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

    @GetMapping("/byUserId/{userId}")
    //@PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<ReservationDto>> getFutureReservationByUserId(@PathVariable Integer userId) {
        List<ReservationDto> reservationsDto = reservationService.getFutureReservationsByUserId(userId);

        if (reservationsDto != null) {
            return new ResponseEntity<>(reservationsDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byAdminId/{adminId}")
    //@PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<ReservationDto>> getFutureReservationByAdminId(@PathVariable Integer adminId) {
        List<ReservationDto> reservationsDto = reservationService.getFutureReservationsByAdminId(adminId);

        if (reservationsDto != null) {
            return new ResponseEntity<>(reservationsDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pastByUserId/{userId}")
    //@PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<ReservationDto>> getPastReservationsByUserId(@PathVariable Integer userId) {
        List<ReservationDto> reservationsDto = reservationService.getPastReservationsByUserId(userId);

        if (reservationsDto != null) {
            return new ResponseEntity<>(reservationsDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pastByAdminId/{adminId}")
    //@PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<ReservationDto>> getPastReservationsByAdminId(@PathVariable Integer adminId) {
        List<ReservationDto> reservationsDto = reservationService.getPastReservationsByAdminId(adminId);

        if (reservationsDto != null) {
            return new ResponseEntity<>(reservationsDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    //@PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        ReservationDto createdReservation = reservationService.createReservation(reservationDto);

    if (createdReservation != null) {
        emailService.sendReservationEmail(createdReservation, userService.findById(reservationDto.getCustomerId()).getEmail());
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    }

    @PutMapping("/cancelReservation")
    //@PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ReservationCancelationDTO> cancelReservation(@RequestBody ReservationDto reservationDto){

        ReservationCancelationDTO canceledReservation = reservationService.cancelReservation(reservationDto);
        if(canceledReservation == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>(canceledReservation,HttpStatus.OK);
    }

    @PutMapping("/cancelReservationQR")
    //@PreAuthorize("hasRole('COMPANYADMIN')")
    public ResponseEntity<ReservationCancelationDTO> cancelReservationQR(@RequestBody ReservationDto reservationDto){

        ReservationCancelationDTO canceledReservation = reservationService.cancelReservationQR(reservationDto);
        if(canceledReservation == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>(canceledReservation,HttpStatus.OK);
    }

    @PutMapping("/pickUpReservation")
    //@PreAuthorize("hasRole('COMPANYADMIN')")
    public ResponseEntity<ReservationCancelationDTO> pickUpReservation(@RequestBody ReservationDto reservationDto){

        ReservationCancelationDTO canceledReservation = reservationService.pickUpReservation(reservationDto);
        if(canceledReservation == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else
            emailService.sendConfirmationEmail(canceledReservation, userService.findById(reservationDto.getCustomerId()).getEmail());
            return new ResponseEntity<>(canceledReservation,HttpStatus.OK);
    }
}
