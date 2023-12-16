package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Dto.UserDto;
import com.ISA.ISAProject.Services.CompanyService;
import com.ISA.ISAProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService _userService;

    @PostMapping(value = "/createSystemAdmin", consumes = "application/json")
    public ResponseEntity<UserDto> registerSystemAdmin(@Valid @RequestBody UserDto userDto) {
        try {

            UserDto newSystemAdmin = _userService.registerSystemAdmin(userDto);

            if (newSystemAdmin == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(newSystemAdmin, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
