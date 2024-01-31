package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.ContractDto;
import com.ISA.ISAProject.Dto.ContractFrontDto;
import com.ISA.ISAProject.Model.Contract;
import com.ISA.ISAProject.Model.ContractEquipment;
import com.ISA.ISAProject.Model.Equipment;
import com.ISA.ISAProject.Repository.CompanyRepository;
import com.ISA.ISAProject.Repository.ContractEquipmentRepository;
import com.ISA.ISAProject.Repository.ContractRepository;
import com.ISA.ISAProject.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.*;

@Service
public class ContractService {

    @Autowired
    private EquipmentRepository _equipmentRepository;

    @Autowired
    private CompanyRepository _companyRepository;

    @Autowired
    private ContractRepository _contractRepository;

    @Autowired
    private ContractEquipmentRepository _conEqRepository;

    public boolean isContractValid(ContractDto contractDto){
        for (String eq:
             contractDto.getEquipmentNames()) {
            if(_equipmentRepository.findEquipmentByName(eq) == null){
                return false;
            }
        }
        if(contractDto.getEquipmentNames().size() != contractDto.getQuantity().size()){
            return false;
        }
        String companyName = contractDto.getCompanyName();
        if(_companyRepository.findCompanyByName(companyName) == null){
            return false;
        }
        return true;
    }

    public void createNew(ContractDto contractDto){
        Contract contract = new Contract(contractDto.getExactDeliveryTime(), contractDto.getHospitalName(), contractDto.getCompanyName(), contractDto.getHospitalAddressLat(), contractDto.getHospitalAddressLong());
        _contractRepository.save(contract);

        for (int i = 0; i < contractDto.getEquipmentNames().size(); i++) {
            Equipment equipment = _equipmentRepository.findEquipmentByName(contractDto.getEquipmentNames().get(i));
            if (equipment != null) {
                ContractEquipment contractEquipment = new ContractEquipment(contractDto.getQuantity().get(i));
                contractEquipment.setContract(contract);
                contractEquipment.setEquipment(equipment);
                _conEqRepository.save(contractEquipment);
            }
        }
    }

    public void updateOld(ContractDto contractDto) {
        Contract contract = _contractRepository.findContractByHospitalName(contractDto.getHospitalName());

        contract.setCompanyName(contractDto.getCompanyName());
        contract.setExactDeliveryTime(contractDto.getExactDeliveryTime());
        contract.setHospitalAddressLat(contractDto.getHospitalAddressLat());
        contract.setHospitalAddressLong(contractDto.getHospitalAddressLong());

        for (int i = 0; i < contractDto.getEquipmentNames().size(); i++) {
            Equipment equipment = _equipmentRepository.findEquipmentByName(contractDto.getEquipmentNames().get(i));
            if (equipment != null) {
                ContractEquipment contractEquipment = _conEqRepository.findContractEquipmentByContractAndEquipment(contract, equipment);
                if (contractEquipment != null) {
                    contractEquipment.setQuantity(contractDto.getQuantity().get(i));
                } else {
                    contractEquipment = new ContractEquipment(contractDto.getQuantity().get(i));
                    contractEquipment.setContract(contract);
                    contractEquipment.setEquipment(equipment);
                }
                _conEqRepository.save(contractEquipment);
            }
        }

        _contractRepository.save(contract);
    }

    public boolean isContractNew(ContractDto contractDto){
        List<Contract> contracts = _contractRepository.findAll();
        if(contracts.isEmpty()){
            return true;
        }
        for (Contract contract:contracts) {
            if(Objects.equals(contract.getHospitalName(), contractDto.getHospitalName())){
                return false;
            }
        }
        return true;
    }


    public void createContract(ContractDto contractDto){
        if(this.isContractValid(contractDto)){
            if(this.isContractNew(contractDto)) {
                this.createNew(contractDto);
            }
            else{
                this.updateOld(contractDto);
            }
        }
    }


    public List<ContractFrontDto> getAllContracts(){
        List<Contract> contracts = _contractRepository.findAll();
        List<ContractFrontDto> contractFrontDtos = new ArrayList<>();
        for (Contract contract: contracts) {
            ContractFrontDto contractFrontDto = new ContractFrontDto(contract);
            contractFrontDtos.add(contractFrontDto);
        }
        return  contractFrontDtos;
    }


    public List<ContractFrontDto> getAllContractsByCompanyName(String name) {
        List<Contract> contracts = _contractRepository.findContractsByCompanyName(name);
        List<ContractFrontDto> contractFrontDtos = new ArrayList<>();
        for (Contract contract: contracts) {
            ContractFrontDto contractFrontDto = new ContractFrontDto(contract);
            contractFrontDtos.add(contractFrontDto);
        }
        return  contractFrontDtos;
    }


    public void changeDate(String response) {
        Contract contract = _contractRepository.findContractByHospitalName(response);
        if(contract != null){

            System.out.println("PRE" + contract.getExactDeliveryTime());
            LocalDateTime newDate = contract.getExactDeliveryTime().plusMonths(1);
            System.out.println("POSLE" + newDate);
            contract.setExactDeliveryTime(newDate);
            _contractRepository.save(contract);
        }
    }
    public void nextDate() {
        List<ContractFrontDto> contracts = getAllContracts();

        LocalDateTime today = LocalDateTime.now().withNano(0).withSecond(0); // Get current date and time with seconds and nanoseconds set to zero
        LocalDate todayDate = today.toLocalDate();

        for (ContractFrontDto contract:contracts) {
            LocalDateTime deliveryTime = contract.getExactDeliveryTime();
            LocalDate deliveryDate = deliveryTime.toLocalDate(); // Extract the date part

            if (deliveryDate.equals(todayDate)){
                LocalDateTime newDate = contract.getExactDeliveryTime().plusMonths(1);
                contract.setExactDeliveryTime(newDate);
                Contract contract1 = _contractRepository.findContractByHospitalName(contract.getHospitalName());
                _contractRepository.save(contract1);
            }

        }
    }
}
