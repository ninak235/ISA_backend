package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.CompanyAdminDto;
import com.ISA.ISAProject.Model.CompanyAdmin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyAdminMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyAdminMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CompanyAdminDto mapCompanyAdminToDto(CompanyAdmin companyAdmin) {
        return modelMapper.map(companyAdmin, CompanyAdminDto.class);
    }

    public List<CompanyAdminDto> mapCompanyAdminsToDto(List<CompanyAdmin> companyAdmins) {
        return companyAdmins.stream()
                .map(this::mapCompanyAdminToDto)
                .collect(Collectors.toList());
    }
}
