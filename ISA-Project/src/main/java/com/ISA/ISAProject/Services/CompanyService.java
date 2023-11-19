package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Mapper.CompanyMapper;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Equipment;
import com.ISA.ISAProject.Repository.CompanyRepository;
import com.ISA.ISAProject.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository _companyRepository;

    @Autowired
    private EquipmentRepository _equipmentRepository;

    @Autowired
    private CompanyMapper _companyMapper;

    public CompanyService(CompanyMapper companyMapper){
        _companyMapper = companyMapper;
    }

    @Transactional
    public List<CompanyDto> getAllCompanies(){
        List<Company> companies = _companyRepository.findAll();
        return _companyMapper.mapCompaniesToDto(companies);
    }

    @Transactional
    public Company getById(Integer companyId){
        return _companyRepository.getOne(companyId);
    }
    @Transactional
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company newCompany = _companyMapper.dtoToCompany(companyDto);
        _companyRepository.save(newCompany);
        return new CompanyDto(newCompany);
    }

    @CrossOrigin
    @Transactional
    public CompanyDto updateCompany(Integer companyId, CompanyDto updatedCompanyDto) {
        Optional<Company> optionalCompany = _companyRepository.findById(companyId);

        if (optionalCompany.isPresent()) {
            Company existingCompany = optionalCompany.get();

            // Update the existing company with the new data
            Company updatedCompany = _companyMapper.dtoToCompany(updatedCompanyDto);
            existingCompany.setName(updatedCompany.getName());
            existingCompany.setAdress(updatedCompany.getAdress());
            existingCompany.setDescription(updatedCompany.getDescription());
            //existingCompany.setGrade(updatedCompany.getGrade());

            // Save the updated company
            _companyRepository.save(existingCompany);

            return new CompanyDto(existingCompany);
        } else {
            // Handle the case where the company with the given ID is not found
            return null;
        }
    }

    @Transactional
    public CompanyDto addEquipmentToCompany(Integer companyId, EquipmentDto equipmentDto) {
        // Get the company
        Company company = _companyRepository.findById(companyId).orElse(null);

        if (company != null) {
            // Get the equipment entity (either existing or newly created)

            Equipment equipment = _equipmentRepository.findEquipmentByName(equipmentDto.getName());

            // Add equipment to the company
            company.addEquipment(equipment);
            _companyRepository.save(company);

            return new CompanyDto(company);
        } else {
            return null;
        }
    }

}
