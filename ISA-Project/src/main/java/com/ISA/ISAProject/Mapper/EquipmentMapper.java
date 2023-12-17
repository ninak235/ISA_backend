package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.EquipmentCompanyDto;
import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Model.Equipment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EquipmentMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public EquipmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EquipmentDto mapEquipmentToDto(Equipment equipment) {
        return modelMapper.map(equipment, EquipmentDto.class);
    }

    public EquipmentCompanyDto mapEquipmentCompanyToDto(Equipment equipment) {
        return modelMapper.map(equipment, EquipmentCompanyDto.class);
    }

    public Equipment mapDtoToEntity(EquipmentDto equipmentDto) {
        return modelMapper.map(equipmentDto, Equipment.class);
    }

    public List<EquipmentDto> mapEquipmentsToDto(List<Equipment> equipments) {
        return equipments.stream()
                .map(this::mapEquipmentToDto)
                .collect(Collectors.toList());
    }

    public Set<Equipment> mapDtosToEntities(List<EquipmentDto> equipmentDtos) {
        return equipmentDtos.stream()
                .map(this::mapDtoToEntity)
                .collect(Collectors.toSet());
    }


    public List<EquipmentCompanyDto> mapEquipmentCompanysToDto(List<Equipment> equipments) {
        return equipments.stream()
                .map(this::mapEquipmentCompanyToDto)
                .collect(Collectors.toList());
    }
}
