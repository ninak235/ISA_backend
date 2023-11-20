package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.EquipmentCompanyDto;
import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Mapper.EquipmentMapper;
import com.ISA.ISAProject.Model.Equipment;
import com.ISA.ISAProject.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository _equipmentRepository;
    @Autowired
    private EquipmentMapper _equipmentMapper;

    @Transactional
    public List<EquipmentCompanyDto> getAllEquipment() {
        List<Equipment> equipments = _equipmentRepository.findAll();
        System.out.println(equipments);
        return _equipmentMapper.mapEquipmentCompanysToDto(equipments);
    }

    @Override
    public String toString() {
        return "";
    }

    @Transactional
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


    @Transactional
    public EquipmentDto createEquipment(EquipmentDto equipmentDto) {
        // Map EquipmentDto to Equipment entity and save it
        Equipment equipment = _equipmentRepository.save(_equipmentMapper.mapDtoToEntity(equipmentDto));
        return new EquipmentDto(equipment);
    }

}
