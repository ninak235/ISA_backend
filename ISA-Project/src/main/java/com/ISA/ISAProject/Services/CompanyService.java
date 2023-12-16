package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.*;
import com.ISA.ISAProject.Mapper.CompanyMapper;
import com.ISA.ISAProject.Model.*;
import com.ISA.ISAProject.Repository.CompanyRepository;
import com.ISA.ISAProject.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.ISA.ISAProject.Mapper.CompanyMapper;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Equipment;
import com.ISA.ISAProject.Repository.CompanyRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import lombok.ToString;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;

import java.util.Optional;

import java.util.*;

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
    public List<CompanyEquipmentDto> getAllCompanies() {
        List<Company> companies = _companyRepository.findAll();
        return _companyMapper.mapCompaniesEquipmentToDto(companies);
    }

    @Transactional
    public Company getById(Integer companyId){
        return _companyRepository.findById(companyId).orElse(null);
    }

    @Transactional
    public Company getByName(String companyName){
        return _companyRepository.findCompanyByName(companyName);
    }
    @Transactional
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company newCompany = _companyMapper.dtoToCompany(companyDto);
        _companyRepository.save(newCompany);
        return new CompanyDto(newCompany);
    }
    /*
    @CrossOrigin
    @Transactional
    public CompanyDto updateCompany(Integer companyId, CompanyDto updatedCompanyDto) {
        Optional<Company> optionalCompany = _companyRepository.findById(companyId);

        if (optionalCompany.isPresent()) {
            Company existingCompany = optionalCompany.get();

            // Update the existing company with the new data
            Company updatedCompany = _companyMapper.dtoToCompany(updatedCompanyDto);
            existingCompany.setName(updatedCompany.getName());
            existingCompany.setAdress(updatedCompany.getAddress());
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

     */

    @Transactional
    public Company updateCompany(String oldCompanyName, CompanyDto companyDto) {
        Company company = _companyMapper.dtoToCompany(companyDto);
        Company updatedCompany = _companyRepository.findCompanyByName(oldCompanyName);

        updatedCompany.setName(company.getName());
        updatedCompany.setAdress(company.getAddress());
        updatedCompany.setDescription(company.getDescription());
        updatedCompany.setGrade(company.getGrade());


        _companyRepository.save(updatedCompany);

        return updatedCompany;
    }


    @Transactional
    public CompanyEquipmentDto addEquipmentToCompany(Integer companyId, EquipmentDto equipmentDto) {
        Company company = _companyRepository.findById(companyId).orElse(null);

        if (company != null) {
            Equipment equipment = _equipmentRepository.findEquipmentByName(equipmentDto.getName());
            company.addEquipment(equipment);
            _companyRepository.save(company);

            return new CompanyEquipmentDto(company);
        } else {
            return null;
        }
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
    public List<CompanyEquipmentDto> getByGradeCompanies(String grade) {
        List<Company> companies = _companyRepository.findAll();
        List<Company> filteredCompanies = new ArrayList<>();

        for(Company company: companies){
            if(company.getGrade().equalsIgnoreCase(grade)){
                filteredCompanies.add(company);
            }
        }
        return _companyMapper.mapCompaniesEquipmentToDto(filteredCompanies);
    }

}
