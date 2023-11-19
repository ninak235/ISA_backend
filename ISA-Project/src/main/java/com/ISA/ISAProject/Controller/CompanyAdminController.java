package com.ISA.ISAProject.Controller;

import io.swagger.annotations.ApiParam;
import com.ISA.ISAProject.Dto.CompanyAdminDto;
import com.ISA.ISAProject.Services.EmailService;
import com.ISA.ISAProject.Services.CompanyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/companyAdmin")
public class CompanyAdminController {
    @Autowired
    private CompanyAdminService _companyAdminService;
    @Autowired
    private EmailService _emailService;

    @PostMapping(value = "/registerCompanyAdmin", consumes = "application/json")
    public ResponseEntity<Void> registerCompanyAdmin(
            //@ApiParam(value = "CompanyAdmin registration information", required = true)
            @Valid @RequestBody CompanyAdminDto registrationDto) {
        try {
            if (_emailService.isEmailUnique(registrationDto.getEmail())) {
                CompanyAdminDto newCompanyAdmin = _companyAdminService.registerCompanyAdmin(registrationDto);

                if (newCompanyAdmin == null) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
