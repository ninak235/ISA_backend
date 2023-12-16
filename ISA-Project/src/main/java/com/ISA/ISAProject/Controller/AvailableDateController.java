package com.ISA.ISAProject.Controller;


import com.ISA.ISAProject.Services.CompanyAdminService;
import com.ISA.ISAProject.Dto.AvailableDateDto;
import com.ISA.ISAProject.Services.AvailableDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getByCompanyId/{companyId}")
    public ResponseEntity<List<AvailableDateDto>> getByCompanyId(@PathVariable  Integer companyId){
        List<AvailableDateDto> allAvailableDates = availableDateService.getAllAvailableDaysByCompanyId(companyId);
        return new ResponseEntity<>(allAvailableDates, HttpStatus.OK);
    }

    @GetMapping(value = "/getExtraByCompanyId/{companyId}/{selectedDate}")
    public ResponseEntity<List<AvailableDateDto>> getExtraByCompanyId(@PathVariable  Integer companyId, @PathVariable String selectedDate){
        List<AvailableDateDto> allAvailableDates = availableDateService.getExtraAvailableDaysByCompanyId(companyId, selectedDate);
        return new ResponseEntity<>(allAvailableDates, HttpStatus.OK);
    }



}
