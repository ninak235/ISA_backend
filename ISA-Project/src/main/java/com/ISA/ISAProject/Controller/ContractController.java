package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.CompanyEquipmentDto;
import com.ISA.ISAProject.Dto.ContractFrontDto;
import com.ISA.ISAProject.Services.ContractService;
import com.ISA.ISAProject.Services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/contract")
public class ContractController {

    @Autowired
    private ContractService _contractService;
    @Autowired
    private ResponseService _responseService;

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
    @PostMapping(value="/{exchange}/{queue}",consumes = "text/plain")
    public ResponseEntity<String> sendMessageToExchange(@PathVariable("exchange") String exchange, @PathVariable("queue") String queue, @RequestBody String response) {
        if(response.equals("Dostava je zapocela,vasa narudzbina ce biti na vasoj adresi uskoro!") || response.equals( "Dostava stigla!")){
            _responseService.sendToExchange(exchange,queue,response);
            System.out.println("*********DOSTAVA DEO**************");
        }
        else{
            System.out.println("*********CANCEL DEO**************");
            _contractService.changeDate(response);
            _responseService.sendToExchange(exchange,queue,response);
        }
        return ResponseEntity.ok().build();
    }

}
