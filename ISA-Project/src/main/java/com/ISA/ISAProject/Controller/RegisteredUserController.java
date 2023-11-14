package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.UserRegistrationDto;
import com.ISA.ISAProject.Model.User;
import com.ISA.ISAProject.Services.EmailService;
import com.ISA.ISAProject.Services.RegisteredUserService;
import com.ISA.ISAProject.Services.TokenService;
import com.ISA.ISAProject.Token.AccountConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/customer")
public class RegisteredUserController {

    @Autowired
    private RegisteredUserService _userService;
    @Autowired
    private EmailService _emailService;
    @Autowired
    private TokenService _tokenService;

    @PostMapping(value = "/registerUser", consumes = "application/json")
    public ResponseEntity<UserRegistrationDto> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        try {
            if (_emailService.isEmailUnique(registrationDto.getEmail())) {
                UserRegistrationDto newUser = _userService.registerUser(registrationDto);

                if (newUser == null) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                _emailService.sendEmail(registrationDto.getEmail());
                return new ResponseEntity<>(newUser, HttpStatus.OK);
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
            User user = _userService.getByEmail(token.getUser().getEmail());

            if (user != null) {
                user.setEnabled(true);
                _userService.updateUser(user);
                return new ResponseEntity<>("Account successfully confirmed.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found for the provided token.", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid or expired token.", HttpStatus.BAD_REQUEST);
        }
    }
}