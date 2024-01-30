package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.CompanyEquipmentDto;
import com.ISA.ISAProject.Dto.ContractFrontDto;
import com.ISA.ISAProject.Services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/contract")
public class ContractController {

    @Autowired
    private ContractService _contractService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ContractFrontDto>> getAllContracts(){
        List<ContractFrontDto> allContracts = _contractService.getAllContracts();
        return new ResponseEntity<>(allContracts, HttpStatus.OK);
    }

    @GetMapping("/getAllByCompanyName/{compName}")
    public ResponseEntity<List<ContractFrontDto>> getAllContractsByCompanyName(@PathVariable String compName){
        List<ContractFrontDto> allContracts = _contractService.getAllContractsByCompanyName(compName);
        return new ResponseEntity<>(allContracts, HttpStatus.OK);
    }

}
