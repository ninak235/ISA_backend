package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.UserDto;
import com.ISA.ISAProject.Model.User;
import com.ISA.ISAProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService _userService;

    @PostMapping(value = "/createSystemAdmin", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
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


    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        User user = _userService.getById(userId);
        UserDto userDto = new UserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/updateSystemAdmin")
    @PreAuthorize("hasAnyRole('ADMIN', 'COMPANYADMIN')")
    public ResponseEntity<Void> updateSystemAdmin(@Valid @RequestBody UserDto userDto) {
        if (userDto.getId() == null) {
            // Handle the case where the ID is null (e.g., return a bad request response)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = _userService.updateSystemAdmin(userDto);

        if (user != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
