package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Services.EquipmentService;
import com.ISA.ISAProject.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService _equipmentService;
    @PostMapping("/equipment")
    public ResponseEntity<EquipmentDto> createEquipment(@RequestBody EquipmentDto equipmentDto) {
        // Call EquipmentService to create equipment
        EquipmentDto createdEquipment = _equipmentService.createEquipment(equipmentDto);

        if (createdEquipment != null) {
            return new ResponseEntity<>(createdEquipment, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
