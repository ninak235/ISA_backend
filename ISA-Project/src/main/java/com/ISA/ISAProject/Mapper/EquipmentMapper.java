package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.CompanyEquipmentDto;
import com.ISA.ISAProject.Dto.EquipmentCompanyDto;
import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Model.CompanyEquipment;
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

    // Add this method to map CompanyEquipmentDto to CompanyEquipment
    public CompanyEquipment mapCompanyEquipmentDtoToEntity(CompanyEquipmentDto companyEquipmentDto) {
        CompanyEquipment companyEquipment = modelMapper.map(companyEquipmentDto, CompanyEquipment.class);
        // Assuming that equipmentDto.getId() returns the equipment ID
        Equipment equipment = new Equipment();
        equipment.setId(companyEquipmentDto.getId());
        companyEquipment.setEquipment(equipment);
        return companyEquipment;
    }

    // Add this method to map CompanyEquipmentDto list to CompanyEquipment set
    public Set<CompanyEquipment> mapCompanyEquipmentDtosToEntities(List<CompanyEquipmentDto> companyEquipmentDtos) {
        return companyEquipmentDtos.stream()
                .map(this::mapCompanyEquipmentDtoToEntity)
                .collect(Collectors.toSet());
    }

    // Add this method to map CompanyEquipment to CompanyEquipmentDto
    public CompanyEquipmentDto mapCompanyEquipmentToDto(CompanyEquipment companyEquipment) {
        return modelMapper.map(companyEquipment, CompanyEquipmentDto.class);
    }

    // Add this method to map CompanyEquipment list to CompanyEquipmentDto list
    public List<CompanyEquipmentDto> mapCompanyEquipmentsToDtos(List<CompanyEquipment> companyEquipments) {
        return companyEquipments.stream()
                .map(this::mapCompanyEquipmentToDto)
                .collect(Collectors.toList());
    }

    public Set<CompanyEquipment> mapEquipmentDtosToCompanyEquipmentEntities(List<EquipmentDto> equipmentDtos) {
        return equipmentDtos.stream()
                .map(this::mapEquipmentDtoToCompanyEquipment)
                .collect(Collectors.toSet());
    }

    private CompanyEquipment mapEquipmentDtoToCompanyEquipment(EquipmentDto equipmentDto) {
        CompanyEquipment companyEquipment = new CompanyEquipment();
        Equipment equipment = mapDtoToEntity(equipmentDto);

        // Set other properties of companyEquipment if needed
        // For example, you might need to set the quantity based on the DTO

        companyEquipment.setEquipment(equipment);
        return companyEquipment;
    }


}
