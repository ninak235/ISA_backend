package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Enum.TypeOfEquipment;
import com.ISA.ISAProject.Mapper.CompanyMapper;
import com.ISA.ISAProject.Mapper.EquipmentMappper;
import com.ISA.ISAProject.Model.Company;
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
    private EquipmentMappper _equipmentMapper;

    @Transactional
    public List<EquipmentDto> getAllEquipment() {
        List<Equipment> equipments = _equipmentRepository.findAll();
        return _equipmentMapper.mapEquipmentsToDto(equipments);
    }

    @Override
    public String toString() {
        return "";
    }

    @Transactional
    public List<EquipmentDto> getByGradeEquipment(String grade) {
        List<Equipment> equipments = _equipmentRepository.findAll();
        List<Equipment> filteredEquipments = new ArrayList<>();

        for(Equipment equipment: equipments){
            if(equipment.getGrade().equalsIgnoreCase(grade)){
                filteredEquipments.add(equipment);
            }
        }
        return _equipmentMapper.mapEquipmentsToDto(filteredEquipments);
    }



    @Transactional
    public List<EquipmentDto> getByTypeEquipment(String type) {
        List<Equipment> equipments = _equipmentRepository.findAll();
        List<Equipment> filteredEquipments = new ArrayList<>();

        for(Equipment equipment: equipments){
            if(equipment.getTypeOfEquipment().toString().equalsIgnoreCase(type)){
                filteredEquipments.add(equipment);
            }
        }
        return _equipmentMapper.mapEquipmentsToDto(filteredEquipments);
    }

}
