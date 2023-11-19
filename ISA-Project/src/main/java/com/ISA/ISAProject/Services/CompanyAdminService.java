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

@Service
public class CompanyAdminService {
    @Autowired
    private CompanyAdminRepository companyAdminRepository;

    @Autowired
    private final UserMapper _userMapper;

    @Autowired
    private final CompanyAdminMapper _companyAdminMapper;

    @Autowired
    private final CompanyService _companyService;

    public CompanyAdminService(UserMapper userMapper, CompanyAdminMapper companyAdminMapper, CompanyService companyService){
        _userMapper = userMapper;
        _companyAdminMapper = companyAdminMapper;
        _companyService = companyService;
    }

    @Transactional
    public List<CompanyAdminDto> getAllCompanyAdmins() {
        List<CompanyAdmin> companyAdmins = companyAdminRepository.findAll();
        return _companyAdminMapper.mapCompanyAdminsToDto(companyAdmins);
    }

    @Transactional
    public CompanyAdminDto getCompanyAdminById(Integer adminId) {
        Optional<CompanyAdmin> optionalCompanyAdmin = companyAdminRepository.findById(adminId);
        return optionalCompanyAdmin.map(_companyAdminMapper::mapCompanyAdminToDto).orElse(null);
    }

    @Transactional
    public CompanyAdminDto createCompanyAdmin(CompanyAdminDto dto, Integer companyId) {
        CompanyAdmin admin = _userMapper.dtoToAdmin(dto);
        Company company = _companyService.getById(companyId);
        admin.setCompany(company);
        companyAdminRepository.save(admin);

        return new CompanyAdminDto(admin.getUser(),admin.getCompany());
    }

    @CrossOrigin
    @Transactional
    public CompanyAdminDto updateCompanyAdmin(Integer adminId, CompanyAdminDto updatedDto, Integer companyId) {
        Optional<CompanyAdmin> optionalCompanyAdmin = companyAdminRepository.findById(adminId);

        if (optionalCompanyAdmin.isPresent()) {
            CompanyAdmin existingAdmin = optionalCompanyAdmin.get();

            // Update the existing admin with the new data
            CompanyAdmin mappedAdmin = _userMapper.dtoToAdmin(updatedDto);
            existingAdmin.setUser(mappedAdmin.getUser());
            Company company = _companyService.getById(companyId);
            existingAdmin.setCompany(company);

            // Save the updated admin
            companyAdminRepository.save(existingAdmin);

            return new CompanyAdminDto(existingAdmin.getUser(),existingAdmin.getCompany());
        } else {
            // Handle the case where the admin with the given ID is not found
            return null;
        }
    }



    // Additional methods for updating, deleting, and other company admin-related actions
}
