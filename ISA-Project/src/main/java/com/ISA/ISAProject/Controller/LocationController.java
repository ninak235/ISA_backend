package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Dto.CompanyEquipmentDto;
import com.ISA.ISAProject.Dto.LocationDto;
import com.ISA.ISAProject.Services.CompanyService;
import com.ISA.ISAProject.Services.LocationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/location")
public class LocationController {
    @Autowired
    private LocationService _locationService;

    @PostMapping(value = "/createLocation", consumes = "application/json")
    //@PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<LocationDto> createLocation(@Valid @RequestBody LocationDto locationDto) {
        try {

            LocationDto newLocation = _locationService.createLocation(locationDto);

            if (newLocation == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(newLocation, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @GetMapping("/geocode")
    public ResponseEntity<?> geocodeAddress(@RequestParam String address) {
        try {
            // Poziv servisa za geokodiranje i vraćanje rezultata
            // Prilagodite kako želite da rukujete rezultatima (prosleđivanje DTO-a ili nešto drugo)
            return new ResponseEntity<>(_locationService.geocodeAddress(address), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error geocoding address", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

}
