package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Dto.LoyalityProgramDto;
import com.ISA.ISAProject.Services.CompanyService;
import com.ISA.ISAProject.Services.LoyalityProgramService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/loyalityProgram")
public class LoyalityProgramController {

    @Autowired
    private LoyalityProgramService _loyalityProgramService;

    @PostMapping(value = "/defineLoyalityProgram", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<LoyalityProgramDto> defineLoyalityProgram(@Valid @RequestBody LoyalityProgramDto loyalityProgramDto) {
        try {

            LoyalityProgramDto newLoyalityProgram = _loyalityProgramService.defineLoyalityProgram(loyalityProgramDto);

            if (newLoyalityProgram == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(newLoyalityProgram, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
