package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.UserRegistrationDto;
import com.ISA.ISAProject.Services.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/registeredUser")
public class RegisteredUserController {

    @Autowired
    private RegisteredUserService _userService;


    @PostMapping(value ="/registerUser",consumes = "application/json")
    public ResponseEntity<UserRegistrationDto> registerUser(@RequestBody UserRegistrationDto registrationDto){

        UserRegistrationDto newUser = _userService.registerUser(registrationDto);
        if(newUser == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newUser,HttpStatus.OK);
    }
}
