package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.*;
import com.ISA.ISAProject.Mapper.ComEqMapper;
import com.ISA.ISAProject.Mapper.CompanyMapper;
import com.ISA.ISAProject.Mapper.EquipmentMapper;
import com.ISA.ISAProject.Model.*;
import com.ISA.ISAProject.Repository.CompanyEquipmentRepository;
import com.ISA.ISAProject.Repository.CompanyRepository;
import com.ISA.ISAProject.Repository.EquipmentRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.ISA.ISAProject.Mapper.CompanyMapper;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Equipment;
import com.ISA.ISAProject.Repository.CompanyRepository;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import lombok.ToString;

import org.springframework.transaction.annotation.Transactional;
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
    private CompanyEquipmentRepository _companyEquipmentRepository;


    @Autowired
    private EquipmentRepository _equipmentRepository;

    @Autowired
    private CompanyMapper _companyMapper;

    @Autowired
    private EquipmentMapper _equipmentMapper;

    @Autowired
    private ComEqMapper _comEqMapper;

    public CompanyService(CompanyMapper companyMapper, EquipmentMapper equipmentMapper){
        _companyMapper = companyMapper;
        _equipmentMapper = equipmentMapper;
    }

    public List<CompanyEquipmentDto> getAllCompanies() {
        List<Company> companies = _companyRepository.findAll();
        return _companyMapper.mapCompaniesEquipmentToDto(companies);
    }

    /*
    @Transactional
    public LocationDto getLocationForCompany(Integer comapnyId){
        Company company = _companyRepository.findById(comapnyId).orElse(null);

        if (company != null) {
            // Ako kompanija postoji, dohvati podatke o lokaciji
            Location location = company.getLocation();

            if (location != null) {
                // Ako postoji lokacija, mapiraj je na LocationDto i vrati
                LocationDto locationDto = new LocationDto();
                locationDto.setId(location.getId());
                locationDto.setAddress(location.getAddress());
                locationDto.setCity(location.getCity());
                locationDto.setCountry(location.getCountry());
                locationDto.setLatitude(location.getLatitude());
                locationDto.setLongitude(location.getLongitude());

                return locationDto;
            }
        }

        // Ako ne postoji kompanija ili nema podataka o lokaciji, vrati null
        return null;
    }*/


    public Company getById(Integer companyId){
        return _companyRepository.findById(companyId).orElse(null);
    }

    @Cacheable(
            value = "companyCache",
            key ="#companyName")
    public Company getByName(String companyName){
        return _companyRepository.findCompanyByName(companyName);
    }


    /*
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company newCompany = _companyMapper.dtoToCompany(companyDto);
        _companyRepository.save(newCompany);
        return new CompanyDto(newCompany);
    }
     */
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

    public Company updateCompany(String oldCompanyName, CompanyDto companyDto) {
        Company company = _companyMapper.dtoToCompany(companyDto);
        Company updatedCompany = _companyRepository.findCompanyByName(oldCompanyName);

        updatedCompany.setName(company.getName());
        updatedCompany.setLocation(company.getLocation());
        updatedCompany.setDescription(company.getDescription());
        updatedCompany.setGrade(company.getGrade());


        _companyRepository.save(updatedCompany);

        return updatedCompany;
    }

    //@Transactional
    public Company changeCompanyEquipment(String companyName, Integer oldId, Integer newId, Integer updatedQuantity) {
        System.out.println("Updating company equipment for company: " + companyName);

        // Find the company by name
        Company existingCompany = _companyRepository.findCompanyByName(companyName);

        // Find the specific CompanyEquipment based on companyId and oldId
        Optional<CompanyEquipment> optionalCompanyEquipment = existingCompany.getCompanyEquipmentSet().stream()
                .filter(equipment -> equipment.getEquipment().getId().equals(oldId))
                .findFirst();

        if (optionalCompanyEquipment.isPresent()) {
            CompanyEquipment companyEquipment = optionalCompanyEquipment.get();

            // Fetch the Equipment entity with newId from the database
            Optional<Equipment> optionalNewEquipment = _equipmentRepository.findById(newId);
            companyEquipment.setQuantity(updatedQuantity);
            if (optionalNewEquipment.isPresent()) {
                Equipment newEquipment = optionalNewEquipment.get();
                // Set the new equipment for the found CompanyEquipment
                companyEquipment.setEquipment(newEquipment);

                System.out.println("Updated Company: " + existingCompany);
                System.out.println("Updated CompanyEquipmentSet: " + existingCompany.getCompanyEquipmentSet());
            } else {
                // Handle the case where the equipment with newId is not found
                System.out.println("Equipment with newId not found");
            }
            // Save the updated company
            _companyRepository.save(existingCompany);
        } else {
            // Handle the case where the CompanyEquipment with oldId is not found
            System.out.println("CompanyEquipment with oldId not found");
        }

        return existingCompany;
    }

    //@Transactional
    public Company deleteCompanyEquipment(String companyName, Integer oldId) {
        System.out.println("Deleting company equipment for company: " + companyName);

        // Find the company by name
        Company existingCompany = _companyRepository.findCompanyByName(companyName);
        System.out.println("Updated CompanyEquipmentSet: " + existingCompany.getCompanyEquipmentSet());

        // Find the specific CompanyEquipment based on companyId and oldId
        Optional<CompanyEquipment> optionalCompanyEquipment = existingCompany.getCompanyEquipmentSet().stream()
                .filter(equipment -> equipment.getEquipment().getId().equals(oldId))
                .findFirst();

        if (optionalCompanyEquipment.isPresent()) {
            CompanyEquipment companyEquipment = optionalCompanyEquipment.get();

            // Remove the CompanyEquipment from the company's set
            existingCompany.getCompanyEquipmentSet().remove(companyEquipment);
            _companyEquipmentRepository.deleteByCompanyIdAndEquipmentId(existingCompany.getId(), oldId);

            // Save the updated company
            _companyRepository.save(existingCompany);

            System.out.println("Updated Company: " + existingCompany);
            System.out.println("Updated CompanyEquipmentSet: " + existingCompany.getCompanyEquipmentSet());
        } else {
            // Handle the case where the CompanyEquipment with oldId is not found
            System.out.println("CompanyEquipment with oldId not found");
        }

        return existingCompany;
    }



    //@Transactional
    public Company addEquipmentToCompany(String companyName, Integer equipmentId) {
        System.out.println("Adding equipment to company: " + companyName);

        // Find the company by name
        Company existingCompany = _companyRepository.findCompanyByName(companyName);

        // Fetch the Equipment entity with equipmentId from the database
        Optional<Equipment> optionalEquipment = _equipmentRepository.findById(equipmentId);

        if (optionalEquipment.isPresent()) {
            Equipment equipmentToAdd = optionalEquipment.get();

            // Check if the company already has this equipment
            boolean alreadyHasEquipment = existingCompany.getCompanyEquipmentSet().stream()
                    .anyMatch(companyEquipment -> companyEquipment.getEquipment().getId().equals(equipmentId));

            if (!alreadyHasEquipment) {
                // Create a new CompanyEquipment entity
                CompanyEquipment newCompanyEquipment = new CompanyEquipment();
                newCompanyEquipment.setQuantity(1); // You can set the quantity as needed
                newCompanyEquipment.setEquipment(equipmentToAdd);
                newCompanyEquipment.setCompany(existingCompany);

                // Add the new CompanyEquipment to the company's set
                existingCompany.getCompanyEquipmentSet().add(newCompanyEquipment);

                // Save the updated company
                _companyRepository.save(existingCompany);

                System.out.println("Updated Company: " + existingCompany);
                System.out.println("Updated CompanyEquipmentSet: " + existingCompany.getCompanyEquipmentSet());
            } else {
                // Handle the case where the company already has this equipment
                System.out.println("Company already has this equipment");
            }
        } else {
            // Handle the case where the equipment with equipmentId is not found
            System.out.println("Equipment with equipmentId not found");
        }

        return existingCompany;
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

    @Transactional
    public ComEqDto getComEq(Integer companyId, Integer equipmentId) {
        Company company1 = _companyRepository.findCompanyById(companyId);
        Equipment equipment1 = _equipmentRepository.findEquipmentById(equipmentId);
        if(company1 != null && equipment1 != null){
            CompanyEquipment companyEquipment = _companyEquipmentRepository.findByCompanyAndEquipment(company1, equipment1);
            return _comEqMapper.mapCompanyEquipmentToDto(companyEquipment);
        }
        return null;
    }
}
