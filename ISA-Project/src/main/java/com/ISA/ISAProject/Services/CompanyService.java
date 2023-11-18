package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Mapper.CompanyMapper;
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
    @Autowired
    private CompanyMapper _companyMapper;

    @Transactional
    public List<CompanyDto> getAllCompanies(){
        List<Company> companies = _companyRepository.findAll();
        return _companyMapper.mapCompaniesToDto(companies);
    }

    public List<CompanyDto> getByGradeCompanies(int grade) {
        List<Company> companies = _companyRepository.findAll();
        //dodati uslov !!!
        return _companyMapper.mapCompaniesToDto(companies);
    }
}
