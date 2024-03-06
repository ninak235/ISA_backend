package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.ComEqDto;
import com.ISA.ISAProject.Dto.CompanyAdminDto;
import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.CompanyEquipment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComEqMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public ComEqMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ComEqDto mapCompanyEquipmentToDto(CompanyEquipment companyEquipment) {
        return modelMapper.map(companyEquipment, ComEqDto.class);
    }

    public List<ComEqDto> mapCompanyEquipmentsToDto(List<CompanyEquipment> companyEquipments) {
        return companyEquipments.stream()
                .map(this::mapCompanyEquipmentToDto)
                .collect(Collectors.toList());
    }
}
