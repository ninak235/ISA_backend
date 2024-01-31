package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.EquipmentCompanyDto;
import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Mapper.EquipmentMapper;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Equipment;
import com.ISA.ISAProject.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository _equipmentRepository;
    @Autowired
    private EquipmentMapper _equipmentMapper;
    @Autowired
    private CompanyService _companyService;
    @Autowired ReservationService _reservationService;


    public List<EquipmentCompanyDto> getAllEquipment() {
        List<Equipment> equipments = _equipmentRepository.findAll();
        System.out.println(equipments);
        return _equipmentMapper.mapEquipmentCompanysToDto(equipments);
    }

    @Override
    public String toString() {
        return "";
    }


    public List<EquipmentCompanyDto> getByGradeEquipment(String grade) {
        List<Equipment> equipments = _equipmentRepository.findAll();
        List<Equipment> filteredEquipments = new ArrayList<>();

        for(Equipment equipment: equipments){
            if(equipment.getGrade().equalsIgnoreCase(grade)){
                filteredEquipments.add(equipment);
            }
        }
        return _equipmentMapper.mapEquipmentCompanysToDto(filteredEquipments);
    }

    @Transactional
    public EquipmentDto getByEquipmentName(String name){
        return new EquipmentDto(_equipmentRepository.findEquipmentByName(name));
    }


    @Transactional
    public List<EquipmentCompanyDto> getByTypeEquipment(String type) {
        List<Equipment> equipments = _equipmentRepository.findAll();
        List<Equipment> filteredEquipments = new ArrayList<>();

        for (Equipment equipment : equipments) {
            if (equipment.getTypeOfEquipment().toString().equalsIgnoreCase(type)) {
                filteredEquipments.add(equipment);
            }
        }
        return _equipmentMapper.mapEquipmentCompanysToDto(filteredEquipments);
    }

    public EquipmentDto createEquipment(EquipmentDto equipmentDto) {
        // Map EquipmentDto to Equipment entity and save it
        Equipment equipment = _equipmentRepository.save(_equipmentMapper.mapDtoToEntity(equipmentDto));
        return new EquipmentDto(equipment);
    }


    public boolean checkIfEquipmentIsReserved(Integer equipmentId, Integer companyId) {
        Equipment equipment = _equipmentRepository.findEquipmentById(equipmentId);
        Company company = _companyService.getById(companyId);

        if (equipment != null && !equipment.isDeleted()) {
            // Check if the equipment is part of any reservation managed by the company's admin
            return _reservationService.isEquipmentReservedByAdmin(equipment, company);
        } else {
            // Equipment not found or deleted
            return false;
        }
    }



}
