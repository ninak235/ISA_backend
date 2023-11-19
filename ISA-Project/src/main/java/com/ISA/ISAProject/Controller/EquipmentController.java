package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService _equipmentService;

    @GetMapping("/getAll")
    public ResponseEntity<List<EquipmentDto>> getAllEquipment(){
        List<EquipmentDto> allEquipments = _equipmentService.getAllEquipment();
        return new ResponseEntity<>(allEquipments, HttpStatus.OK);
    }

    @GetMapping(value = "/byGrade")
    public ResponseEntity<List<EquipmentDto>> getByGrade(@RequestParam String grade){
        List<EquipmentDto> byGradeEquipments = _equipmentService.getByGradeEquipment(grade);
        return new ResponseEntity<>(byGradeEquipments, HttpStatus.OK);
    }

    @GetMapping(value = "/byType")
    public ResponseEntity<List<EquipmentDto>> getByType(@RequestParam String typeOfEquipment){
        List<EquipmentDto> byTypeEquipments = _equipmentService.getByTypeEquipment(typeOfEquipment);
        return new ResponseEntity<>(byTypeEquipments, HttpStatus.OK);
    }

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
