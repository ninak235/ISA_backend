package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.*;
import com.ISA.ISAProject.Mapper.LocationMapper;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Location;
import com.ISA.ISAProject.Services.CompanyService;
import com.ISA.ISAProject.Services.LocationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/company")
public class CompanyController {

    @Autowired
    private CompanyService _companyService;
    @Autowired
    private LocationService _locationService;
    @Autowired
    private LocationMapper _locationMapper;

    @GetMapping("/equipments/getBy/{companyId}/{equipmentId}")
    public ResponseEntity<ComEqDto> getComEq(@PathVariable Integer companyId, @PathVariable Integer equipmentId){
        ComEqDto comEqs = _companyService.getComEq(companyId, equipmentId);
        return new ResponseEntity<>(comEqs, HttpStatus.OK);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<CompanyEquipmentDto>> getAllCompanies(){
        List<CompanyEquipmentDto> allCompanies = _companyService.getAllCompanies();

        /*
        for (CompanyEquipmentDto company : allCompanies) { // Prilagodite prema stvarnoj logici
            LocationDto locationDto = _companyService.getLocationForCompany(company.getId()); // Prilagodite prema stvarnoj logici
            company.setLocationDto(locationDto);
        }*/
        return new ResponseEntity<>(allCompanies, HttpStatus.OK);
    }

    @GetMapping(value = "/{companyId}")
    @PreAuthorize("hasRole('COMPANYADMIN')")
    public ResponseEntity<CompanyEquipmentDto> getCompanyById(@PathVariable Integer companyId){
        Company company = _companyService.getById(companyId);
        CompanyEquipmentDto companyDto = new CompanyEquipmentDto(company);
        return new ResponseEntity<>(companyDto, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{companyName}")
    public ResponseEntity<CompanyEquipmentDto> getCompanyByName(@PathVariable String companyName){
        Company company = _companyService.getByName(companyName);
        CompanyEquipmentDto companyDto = new CompanyEquipmentDto(company);
        return new ResponseEntity<>(companyDto, HttpStatus.OK);
    }

    @GetMapping("/getIdNameAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CompanyIdNameDto>> getAllCompaniesIdName(){
        List<CompanyIdNameDto> allCompaniesIdName = _companyService.getAllCompaniesIdName();
        return new ResponseEntity<>(allCompaniesIdName, HttpStatus.OK);
    }

    @GetMapping(value = "/byGrade")
    public ResponseEntity<List<CompanyEquipmentDto>> getByGrade(@RequestParam String grade){
        List<CompanyEquipmentDto> byGradeCompanies = _companyService.getByGradeCompanies(grade);
        return new ResponseEntity<>(byGradeCompanies, HttpStatus.OK);

    }

    @PostMapping(value = "/registerCompany", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<CompanyDto> registerCompany(@Valid @RequestBody CompanyDto companyDto) {
        try {

            //LocationDto locationDto = companyDto.getLocationDto();
            //LocationDto newLocation = _locationService.createLocation(locationDto);

            // Postavljanje lokacije u CompanyDto
            //companyDto.setLocationDto(newLocation);
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


    /*
        @PostMapping("/create")
        public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
            CompanyDto createdCompany = _companyService.createCompany(companyDto);

            if (createdCompany != null) {
                return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

     */

    @PostMapping("/add-equipment/{companyName}/{equipmentId}")
    public ResponseEntity<CompanyEquipmentDto> addEquipmentToCompany(
            @PathVariable String companyName,
            @PathVariable Integer equipmentId) {

        // Call CompanyService to add equipment to the company
        Company updatedCompany = _companyService.addEquipmentToCompany(companyName, equipmentId);

        if (updatedCompany != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /*
    @PutMapping("/update/{companyId}")
    public ResponseEntity<CompanyDto> updateCompany(
            @PathVariable Integer companyId,
            @RequestBody CompanyDto updatedCompanyDto) {

        CompanyDto updatedCompany = _companyService.updateCompany(companyId, updatedCompanyDto);

        if (updatedCompany != null) {
            return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

     */

    @CrossOrigin
    @PutMapping(value = "/update/{oldCompanyName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateCompany(@PathVariable String oldCompanyName, @Valid @RequestBody CompanyDto companyDto) {
        Company company = _companyService.updateCompany(oldCompanyName, companyDto);
        if (company != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @PutMapping(value = "/update/equipment/change/{oldCompanyName}")
    public ResponseEntity<Void> changeCompanyEquipment(
            @PathVariable String oldCompanyName,
            @RequestParam(name = "oldId") Integer oldId,
            @RequestParam(name = "newId") Integer newId,
            @RequestParam(name = "updatedQuantity") Integer updatedQuantity,
            @Valid @RequestBody Company updatedCompany) {
        _companyService.changeCompanyEquipment(oldCompanyName, oldId, newId, updatedQuantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/update/equipment/delete/{oldCompanyName}")
    public ResponseEntity<Void> deleteCompanyEquipment(
            @PathVariable String oldCompanyName,
            @RequestParam(name = "oldId") Integer oldId,
            @Valid @RequestBody Company updatedCompany) {
        _companyService.deleteCompanyEquipment(oldCompanyName, oldId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
