package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Dto.CompanyIdNameDto;
import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Enum.TypeOfEquipment;
import com.ISA.ISAProject.Services.CompanyService;
import com.ISA.ISAProject.Services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
