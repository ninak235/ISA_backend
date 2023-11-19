package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.CompanyAdminDto;
import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Mapper.CompanyAdminMapper;
import com.ISA.ISAProject.Mapper.UserMapper;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Repository.CompanyAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import com.ISA.ISAProject.Mapper.UserMapper;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.User;
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

    public CompanyAdminService(UserMapper userMapper, CompanyAdminMapper companyAdminMapper, CompanyService companyService) {
        _userMapper = userMapper;
        _companyAdminMapper = companyAdminMapper;
        _companyService = companyService;
    }

    @Transactional
    public List<CompanyAdminDto> getAllCompanyAdmins() {
        List<CompanyAdmin> companyAdmins = _companyAdminRepository.findAll();
        return _companyAdminMapper.mapCompanyAdminsToDto(companyAdmins);
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
        CompanyAdmin companyAdmin = _userMapper.dtoToCompanyAdmin(dto);
        Company company = _companyRepository.getOne(companyAdmin.getCompany().getId());
        //Hibernate.initialize(company.getCompanyAdmin()); // Initialize the collection
        companyAdmin.setCompany(company);
        _companyAdminRepository.save(companyAdmin);

        return new CompanyAdminDto(companyAdmin.getUser(), companyAdmin.getCompany().getId());
    }

    @CrossOrigin
    @Transactional
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

    public User getByEmail(String email) {
        return _userRepository.findByEmailIgnoreCase(email);
    }

    public void updateUser(User user) {
        _userRepository.save(user);
    }
}
