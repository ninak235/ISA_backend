package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.CompanyAdminBasicDto;
import com.ISA.ISAProject.Dto.CompanyAdminDto;
import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Dto.UserDto;
import com.ISA.ISAProject.Mapper.CompanyAdminMapper;
import com.ISA.ISAProject.Mapper.UserMapper;
import com.ISA.ISAProject.Model.*;
import com.ISA.ISAProject.Repository.CompanyAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.ISA.ISAProject.Mapper.UserMapper;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Repository.CompanyAdminRepository;
import com.ISA.ISAProject.Repository.CompanyRepository;
import com.ISA.ISAProject.Repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
@Service
public class CompanyAdminService {

    @Autowired
    private CompanyAdminRepository _companyAdminRepository;

    @Autowired
    private CompanyRepository _companyRepository;

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private final UserMapper _userMapper;

    @Autowired
    private final CompanyAdminMapper _companyAdminMapper;

    @Autowired
    private final CompanyService _companyService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyAdminService(UserMapper userMapper, CompanyAdminMapper companyAdminMapper, CompanyService companyService) {
        _userMapper = userMapper;
        _companyAdminMapper = companyAdminMapper;
        _companyService = companyService;
    }

    @Transactional
    public List<CompanyAdmin> getAllCompanyAdmins() {
        List<CompanyAdmin> companyAdmins = _companyAdminRepository.findAll();

        return companyAdmins;
    }

    @Transactional
    public List<CompanyAdminDto> getCompanyAdmins() {
        List<CompanyAdmin> companyAdmins = _companyAdminRepository.findAll();
        List<CompanyAdminDto> companyAdminDtos = new ArrayList<>();
        for (CompanyAdmin ca: companyAdmins){
            User user = _userRepository.findByUserName(ca.getUser().getUsername());
            CompanyAdminDto caDto = new CompanyAdminDto(user, ca.getCompanyId());
            companyAdminDtos.add(caDto);
        }

        return companyAdminDtos;
    }

    @Transactional
    public CompanyAdminDto getCompanyAdminById(Integer adminId) {
        Optional<CompanyAdmin> optionalCompanyAdmin = _companyAdminRepository.findById(adminId);
        return optionalCompanyAdmin.map(_companyAdminMapper::mapCompanyAdminToDto).orElse(null);
    }
    /* spaletovo
    @Transactional
    public CompanyAdminDto createCompanyAdmin(CompanyAdminDto dto, Integer companyId) {
        CompanyAdmin admin = _userMapper.dtoToAdmin(dto);
        Company company = _companyService.getById(companyId);
        admin.setCompany(company);
        _companyAdminRepository.save(admin);

        return new CompanyAdminDto(admin.getUser(),admin.getCompany());
    }
    */

    public CompanyAdminDto registerCompanyAdmin(CompanyAdminDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        CompanyAdmin companyAdmin = _userMapper.dtoToCompanyAdmin(dto);
        Company company = _companyRepository.getOne(companyAdmin.getCompany().getId());

        //Hibernate.initialize(company.getCompanyAdmin()); // Initialize the collection
        companyAdmin.setCompany(company);
        _companyAdminRepository.save(companyAdmin);

        return new CompanyAdminDto(companyAdmin.getUser(), companyAdmin.getCompany().getId());
    }

    /*
    public CompanyAdminDto updateCompanyAdmin(Integer adminId, CompanyAdminDto updatedDto, Integer companyId) {
        Optional<CompanyAdmin> optionalCompanyAdmin = _companyAdminRepository.findById(adminId);

        if (optionalCompanyAdmin.isPresent()) {
            CompanyAdmin existingAdmin = optionalCompanyAdmin.get();

            // Update the existing admin with the new data
            CompanyAdmin mappedAdmin = _userMapper.dtoToCompanyAdmin(updatedDto);
            existingAdmin.setUser(mappedAdmin.getUser());
            Company company = _companyService.getById(companyId);
            existingAdmin.setCompany(company);

            // Save the updated admin
            _companyAdminRepository.save(existingAdmin);

            return new CompanyAdminDto(existingAdmin.getUser(), existingAdmin.getCompany().getId());
        } else {
            // Handle the case where the admin with the given ID is not found
            return null;
        }
    }

     */
    @Transactional
    public CompanyAdmin updateAdmin(CompanyAdminDto adminDto) {
        CompanyAdmin admin = _userMapper.dtoToCompanyAdmin(adminDto);

        User user = _userRepository.findByEmailIgnoreCase(adminDto.getEmail());
        user.setId(adminDto.getId());
        user.setFirstName(adminDto.getFirstName());
        user.setLastName(adminDto.getLastName());
        user.setPassword(adminDto.getPassword());
        user.setCountry(adminDto.getCountry());
        user.setCity(adminDto.getCity());
        user.setNumber(adminDto.getNumber());

        CompanyAdmin updatedAdmin = _companyAdminRepository.findByUser_Email(admin.getUser().getEmail());

        Integer companyId = updatedAdmin.getCompanyId();
        Company company = _companyService.getById(companyId);

        updatedAdmin.setCompany(company);
        updatedAdmin.setUser(user);
        _companyAdminRepository.save(updatedAdmin);

        return updatedAdmin;
    }


    public User getByEmail(String email) {
        return _userRepository.findByEmailIgnoreCase(email);
    }

    public void updateUser(User user) {
        _userRepository.save(user);
    }

    public CompanyAdmin getById(Integer adminId) {
        return _companyAdminRepository.findById(adminId).orElse(null);
    }
}
