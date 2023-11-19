package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Mapper.EquipmentMapper;
import com.ISA.ISAProject.Mapper.UserMapper;
import com.ISA.ISAProject.Model.Equipment;
import com.ISA.ISAProject.Repository.CompanyRepository;
import com.ISA.ISAProject.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.ExtensionAwareQueryMethodEvaluationContextProvider;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository _equipmentRepository;

    @Autowired
    private EquipmentMapper _equipmentMapper;

    public EquipmentService(EquipmentMapper equipmentMapper){
        _equipmentMapper = equipmentMapper;
    }

    @Transactional
    public EquipmentDto createEquipment(EquipmentDto equipmentDto) {
        // Map EquipmentDto to Equipment entity and save it
        Equipment equipment = _equipmentRepository.save(_equipmentMapper.mapDtoToEntity(equipmentDto));
        return new EquipmentDto(equipment);
    }

}
