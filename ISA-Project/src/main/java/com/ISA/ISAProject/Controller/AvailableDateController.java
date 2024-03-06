package com.ISA.ISAProject.Controller;


import com.ISA.ISAProject.Model.AvailableDate;
import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Dto.ReservationDto;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Services.CompanyAdminService;
import com.ISA.ISAProject.Dto.AvailableDateDto;
import com.ISA.ISAProject.Services.AvailableDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/availableDate")
public class AvailableDateController {

    @Autowired
    private AvailableDateService availableDateService;
    @Autowired
    private CompanyAdminService companyAdminService;
    //private EquipmentService equipmentService;

    @GetMapping("/getAll")
    public ResponseEntity<List<AvailableDateDto>> getAllAvailableDates() {
        List<AvailableDateDto> allAvailableDates = availableDateService.getAllAvailableDays();
        return new ResponseEntity<>(allAvailableDates, HttpStatus.OK);
    }

    @GetMapping("/{availableDateId}")
    public ResponseEntity<AvailableDateDto> getAvailableDateById(@PathVariable Integer availableDateId) {
        AvailableDateDto availableDateDto = availableDateService.getAvailableDateById(availableDateId);

        if (availableDateDto != null) {
            return new ResponseEntity<>(availableDateDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByCompanyId/")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<AvailableDateDto>> getByCompanyId(@RequestParam Integer companyId , @RequestParam Integer userId){
        List<AvailableDateDto> allAvailableDates = availableDateService.getAllAvailableDaysByCompanyId(companyId,userId);
        return new ResponseEntity<>(allAvailableDates, HttpStatus.OK);
    }

    @GetMapping("/getByAdminId/{adminId}")
    //@PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<AvailableDateDto>> getByAdminId(@PathVariable  Integer adminId){
        List<AvailableDateDto> allAvailableDates = availableDateService.getAllAvailableDaysByAdminId(adminId);
        return new ResponseEntity<>(allAvailableDates, HttpStatus.OK);
    }

    @GetMapping(value = "/getExtraByCompanyId/{companyId}/{selectedDate}/{userId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<AvailableDateDto>> getExtraByCompanyId(@PathVariable  Integer companyId, @PathVariable String selectedDate,@PathVariable Integer userId){
        List<AvailableDateDto> allAvailableDates = availableDateService.getExtraAvailableDaysByCompanyId(companyId, selectedDate,userId);
        return new ResponseEntity<>(allAvailableDates, HttpStatus.OK);
    }

    @GetMapping(value = "/getExtraByCompanyIdAndAdminId/{companyName}/{companyAdminId}/{selectedDate}")
    public ResponseEntity<List<AvailableDateDto>> getExtraByCompanyIdAndAdminId(@PathVariable  String companyName, @PathVariable  Integer companyAdminId, @PathVariable String selectedDate){
        List<AvailableDateDto> allAvailableDates = availableDateService.getExtraAvailableDaysByCompanyIdAndAdminId(companyName,companyAdminId, selectedDate);
        return new ResponseEntity<>(allAvailableDates, HttpStatus.OK);
    }

//    @PostMapping("/create")
//    public ResponseEntity<AvailableDate> createAvailableDate(@RequestBody AvailableDateDto availableDateDto) {
//         Implement the logic to create a new available date
//        AvailableDate createdDate = availableDateService.createAvailableDate(availableDateDto);
//        return ResponseEntity.ok(createdDate);
//    }

    @PostMapping("/new")
    public ResponseEntity<AvailableDateDto> createAvailableDate(@RequestBody AvailableDateDto availableDateDto) {
        AvailableDateDto createdDate = availableDateService.createAvailableDate(availableDateDto);

        if (createdDate != null) {
            return new ResponseEntity<>(createdDate, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody AvailableDateDto availableDateDto) {
        AvailableDate availableDate = availableDateService.update(availableDateDto);
        if (availableDate != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
