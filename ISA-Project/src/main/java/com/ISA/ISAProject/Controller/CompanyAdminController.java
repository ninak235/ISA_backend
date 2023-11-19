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

    @PostMapping(value = "/registerCompanyAdmin")
    public ResponseEntity<Void> registerCompanyAdmin(@Valid @RequestBody CompanyAdminDto registrationDto) {
        try {
            if (_emailService.isEmailUnique(registrationDto.getEmail())) {

                System.out.println(registrationDto.getCompanyId());
                System.out.println(registrationDto.getCity());
                System.out.println(registrationDto.getCountry());
                System.out.println(registrationDto.getNumber());
                System.out.println(registrationDto.getPassword());
                System.out.println(registrationDto.getFirstName());
                System.out.println(registrationDto.getLastName());
                System.out.println(registrationDto.getEmail());

                CompanyAdminDto newCompanyAdmin = _companyAdminService.registerCompanyAdmin(registrationDto);
                System.out.println(newCompanyAdmin);

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
