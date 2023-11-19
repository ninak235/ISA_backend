package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.CompanyAdminDto;
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
    private final UserMapper _userMapper;
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private CompanyRepository _companyRepository;

    public CompanyAdminService(UserMapper userMapper){
        _userMapper = userMapper;
    }

    public CompanyAdminDto registerCompanyAdmin(CompanyAdminDto dto){
        CompanyAdmin companyAdmin = _userMapper.dtoToCompanyAdmin(dto);
        Company company = _companyRepository.getOne(companyAdmin.getCompany().getId());
        //Hibernate.initialize(company.getCompanyAdmin()); // Initialize the collection
        companyAdmin.setCompany(company);
        _companyAdminRepository.save(companyAdmin);

        return new CompanyAdminDto(companyAdmin.getUser(), companyAdmin.getCompany().getId());
    }


    public User getByEmail(String email){
        return _userRepository.findByEmailIgnoreCase(email);
    }

    public void updateUser(User user){
        _userRepository.save(user);
    }
}
