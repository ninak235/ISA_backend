package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Model.Company;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    private final ModelMapper mapper;

    @Autowired
    public CompanyMapper(ModelMapper modelMapper){this.mapper = modelMapper;}

    public List<CompanyDto> mapCompaniesToDto(List<Company> companies){
        return companies.stream()
                .map(CompanyDto::new)
                .collect(Collectors.toList());
    }
}
