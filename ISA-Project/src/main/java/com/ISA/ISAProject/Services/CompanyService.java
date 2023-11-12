package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository _companyRepository;

    @Transactional
    public List<CompanyDto> getAllCompanies(){
        List<Company> companies = _companyRepository.findAll();
        return companies.stream()
                .map(CompanyDto::new)
                .collect(Collectors.toList());
    }

}
