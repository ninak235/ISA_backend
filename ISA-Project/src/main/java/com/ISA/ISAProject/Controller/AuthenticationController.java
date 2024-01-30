package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Dto.JwtAuthenticationRequestDto;
import com.ISA.ISAProject.Dto.ReservationDto;
import com.ISA.ISAProject.Dto.UserTokenStateDto;
import com.ISA.ISAProject.Enum.ReservationStatus;
import com.ISA.ISAProject.Model.Reservation;
import com.ISA.ISAProject.Model.User;
import com.ISA.ISAProject.Services.*;
import com.ISA.ISAProject.Token.AccountConfirmationToken;
import com.ISA.ISAProject.Token.PickUpReservationToken;
import com.ISA.ISAProject.Token.ReservationConfirmationToken;
import com.ISA.ISAProject.Token.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmailService _emailService;
    @Autowired
    private CustomerService _customerService;
    @Autowired
    private TokenService _tokenService;
    @Autowired
    private ReservationService _reservationService;
    @Autowired
    private UserService _userService;


    @PostMapping("/login")
    public ResponseEntity<UserTokenStateDto> createAuthenticationToken(@RequestBody JwtAuthenticationRequestDto authenticationRequestDto){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername(), user.getRoles(),user.getId());
        int expiresIn = tokenUtils.getExpiredIn();
        System.out.println(jwt);
        return ResponseEntity.ok(new UserTokenStateDto(jwt, expiresIn));
    }


    @PostMapping(value = "/registerCustomer", consumes = "application/json")
    public ResponseEntity<Void> registerCustomer(@Valid @RequestBody CustomerDto registrationDto) {
        try {
            if (_emailService.isEmailUnique(registrationDto.getEmail())) {
                CustomerDto newUser = _customerService.registerCustomer(registrationDto);

                if (newUser == null) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                _emailService.sendEmail(registrationDto.getEmail());
                return new ResponseEntity<>( HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/confirm-account")
    public ResponseEntity<String>confirmUserAccount(@RequestParam("token") String confirmationToken){

        AccountConfirmationToken token = _tokenService.getConfirmationToken(confirmationToken);
        if (token != null) {
            User user = _customerService.getByEmail(token.getUser().getEmail());

            if (user != null) {
                user.setEnabled(true);
                _customerService.updateUser(user);
                return new ResponseEntity<>("Account successfully confirmed.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found for the provided token.", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid or expired token.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create-reservation")
    public ResponseEntity<Void> reservationConfirmation(){
        ReservationDto manuallyCreatedDto = new ReservationDto();
        manuallyCreatedDto.setDateTime(LocalDateTime.now());
        manuallyCreatedDto.setDuration(2);
        manuallyCreatedDto.setGrade(5);
        manuallyCreatedDto.setStatus(ReservationStatus.Pending);
        manuallyCreatedDto.setCustomerId(1); // Postavite odgovarajući ID kupca
        manuallyCreatedDto.setCompanyAdminId(2); // Postavite odgovarajući ID admina kompanije

        ReservationDto savedDto = _reservationService.createReservation(manuallyCreatedDto);
        _emailService.sendReservationEmail(savedDto,_userService.findById(savedDto.getCustomerId()).getEmail());
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping(value = "/confirm-reservation")
    public ResponseEntity<String>confirmReservation(@RequestParam("token") String reservationToken){

        ReservationConfirmationToken token = _tokenService.getReservationToken(reservationToken);
        if(token != null){
            Reservation reservation = token.getReservation();
            if(reservation != null){
                reservation.setStatus(ReservationStatus.Confirmed);
                _reservationService.updateReservation(reservation);
                return new ResponseEntity<>("Reservation successfully confirmed.", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Reservation not found for the provided token.", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid or expired token.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/pickUp-reservation")
    public ResponseEntity<String>PickUpReservation(@RequestParam("token") String reservationToken){

        PickUpReservationToken token = _tokenService.getPickUpReservationToken(reservationToken);
        if(token != null){
            Reservation reservation = token.getReservation();
            if(reservation != null){
                reservation.setStatus(ReservationStatus.PickedUp);
                _reservationService.updateReservation(reservation);
                return new ResponseEntity<>("Reservation successfully picked up.", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Reservation not found for the provided token.", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid or expired token.", HttpStatus.BAD_REQUEST);
        }
    }
}
