package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Dto.CompanyIdNameDto;
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

    @GetMapping("/getIdNameAll")
    public ResponseEntity<List<CompanyIdNameDto>> getAllCompaniesIdName(){
        List<CompanyIdNameDto> allCompaniesIdName = _companyService.getAllCompaniesIdName();
        return new ResponseEntity<>(allCompaniesIdName, HttpStatus.OK);
    }

    @PostMapping(value = "/registerCompany", consumes = "application/json")
    public ResponseEntity<CompanyDto> registerCompany(@Valid @RequestBody CompanyDto companyDto) {
        try {

                CompanyDto newCompany = _companyService.registerCompany(companyDto);

                if (newCompany == null) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }else{
                    return new ResponseEntity<>(newCompany, HttpStatus.OK);
                }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/byGrade")
    public ResponseEntity<List<CompanyDto>> getByGrade(@RequestParam String grade){
        List<CompanyDto> byGradeCompanies = _companyService.getByGradeCompanies(grade);
        return new ResponseEntity<>(byGradeCompanies, HttpStatus.OK);
    }
}
