package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Dto.CompanyIdNameDto;
import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Mapper.CompanyMapper;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Model.Equipment;
import com.ISA.ISAProject.Repository.CompanyRepository;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.ToString;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository _companyRepository;
    @Autowired
    private CompanyMapper _companyMapper;

    @Transactional
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = _companyRepository.findAll();
        return _companyMapper.mapCompaniesToDto(companies);
    }

    public CompanyDto registerCompany(CompanyDto dto){
        Company comapny = _companyMapper.dtoToCompany(dto);
        _companyRepository.save(comapny);

        return new CompanyDto(comapny);
    }



    public List<CompanyIdNameDto> getAllCompaniesIdName() {
        List<Company> companies = _companyRepository.findAll();

        return companies.stream()
                .map(comp -> new CompanyIdNameDto(comp.getId(), comp.getName()))
                .collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return "";
    }

    @Transactional
    public List<CompanyDto> getByGradeCompanies(String grade) {
        List<Company> companies = _companyRepository.findAll();
        List<Company> filteredCompanies = new ArrayList<>();

        for(Company company: companies){
            if(company.getGrade().equalsIgnoreCase(grade)){
                filteredCompanies.add(company);
            }
        }
        return _companyMapper.mapCompaniesToDto(filteredCompanies);
    }
}
