package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.ContractDto;
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


    @Transactional
    public void createContract(ContractDto contractDto){
        System.out.println("USAO U CREATE");
        if(this.isContractValid(contractDto)){
            System.out.println("SVE JE OK");
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
    }




}
