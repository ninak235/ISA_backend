package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.CompanyAdminDto;
import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Services.CompanyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company/admin")
public class CompanyAdminController {

    @Autowired
    private CompanyAdminService _companyAdminService;

    @PostMapping("/createAdmin/{companyId}")
    public ResponseEntity<CompanyAdminDto> createCompanyAdmin(
            @PathVariable Integer companyId,
            @RequestBody CompanyAdminDto companyAdminDto) {

        CompanyAdminDto createdAdmin = _companyAdminService.createCompanyAdmin(companyAdminDto, companyId);

        if (createdAdmin != null) {
            return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateAdmin/{adminId}/{companyId}")
    public ResponseEntity<CompanyAdminDto> updateCompanyAdmin(
            @PathVariable Integer adminId,
            @PathVariable Integer companyId,
            @RequestBody CompanyAdminDto updatedAdminDto) {

        CompanyAdminDto updatedAdmin = _companyAdminService.updateCompanyAdmin(adminId, updatedAdminDto, companyId);

        if (updatedAdmin != null) {
            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
