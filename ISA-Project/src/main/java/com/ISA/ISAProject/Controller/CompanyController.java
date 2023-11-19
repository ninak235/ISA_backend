package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/company")
public class CompanyController {

    @Autowired
    private CompanyService _companyService;

    @GetMapping("/getAll")
    public ResponseEntity<List<CompanyDto>> getAllCompanies(){
        List<CompanyDto> allCompanies = _companyService.getAllCompanies();
        return new ResponseEntity<>(allCompanies, HttpStatus.OK);
    }

    @GetMapping(value = "/byGrade")
    public ResponseEntity<List<CompanyDto>> getByGrade(@RequestParam String grade){
        List<CompanyDto> byGradeCompanies = _companyService.getByGradeCompanies(grade);
        return new ResponseEntity<>(byGradeCompanies, HttpStatus.OK);
    }
}
