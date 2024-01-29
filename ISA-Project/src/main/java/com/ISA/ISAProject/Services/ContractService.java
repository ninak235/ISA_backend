package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.ContractDto;
import com.ISA.ISAProject.Model.Contract;
import com.ISA.ISAProject.Model.Equipment;
import com.ISA.ISAProject.Repository.CompanyRepository;
import com.ISA.ISAProject.Repository.ContractRepository;
import com.ISA.ISAProject.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContractService {

    @Autowired
    private EquipmentRepository _equipmentRepository;

    @Autowired
    private CompanyRepository _companyRepository;

    @Autowired
    private ContractRepository _contractRepository;

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


    @Transactional
    public void createContract(ContractDto contractDto){
        System.out.println("USAO U CREATE");
        if(this.isContractValid(contractDto)){
            System.out.println("SVE JE OK");
            Contract contract = new Contract(contractDto.getExactDeliveryTime(), contractDto.getHospitalName(), contractDto.getCompanyName(), contractDto.getHospitalAddressLat(), contractDto.getHospitalAddressLong(), contractDto.getQuantity().get(0));
            Set<Equipment> equipmentSet = new HashSet<>();

            for (int i = 0; i < contractDto.getEquipmentNames().size(); i++) {
                Equipment equipment = _equipmentRepository.findEquipmentByName(contractDto.getEquipmentNames().get(i));
                if (equipment != null) {
                    equipmentSet.add(equipment);
                    equipment.getContractsOfEquipment().add(contract);
                }
            }

            contract.setEquipmentNames(equipmentSet);
            _contractRepository.save(contract);
        }
    }




}
