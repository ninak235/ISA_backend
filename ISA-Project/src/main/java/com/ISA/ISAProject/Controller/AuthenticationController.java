package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Dto.JwtAuthenticationRequestDto;
import com.ISA.ISAProject.Dto.UserTokenStateDto;
import com.ISA.ISAProject.Model.User;
import com.ISA.ISAProject.Services.CustomerService;
import com.ISA.ISAProject.Services.EmailService;
import com.ISA.ISAProject.Services.TokenService;
import com.ISA.ISAProject.Services.UserService;
import com.ISA.ISAProject.Token.AccountConfirmationToken;
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


    @PostMapping("/login")
    public ResponseEntity<UserTokenStateDto> createAuthenticationToken(@RequestBody JwtAuthenticationRequestDto authenticationRequestDto, HttpServletResponse response){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

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
}
