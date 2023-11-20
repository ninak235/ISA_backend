package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Dto.CompanyEquipmentDto;
import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Enum.TypeOfUser;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Model.User;
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

    /*
    public Company dtoToCompany(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setAdress(companyDto.getAdress());
        company.setDescription(companyDto.getDescription());
        company.setGrade(companyDto.getGrade());
    */
    public Company dtoToCompany(CompanyDto dto){
        Company company = mapper.map(dto, Company.class);
        return company;
    }

    public List<CompanyEquipmentDto> mapCompaniesEquipmentToDto(List<Company> companies) {
        return companies.stream()
                .map(CompanyEquipmentDto::new)
                .collect(Collectors.toList());
    }
}
