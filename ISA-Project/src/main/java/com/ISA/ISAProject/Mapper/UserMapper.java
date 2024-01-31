package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.*;
import com.ISA.ISAProject.Dto.CompanyAdminDto;
import com.ISA.ISAProject.Model.*;
import com.ISA.ISAProject.Services.CompanyService;
import com.ISA.ISAProject.Services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Autowired
    private RoleService roleService;
    @Autowired
    private CompanyService companyService;


    public Customer dtoToCustomer(CustomerDto dto) {
        User user = modelMapper.map(dto, User.class);
        List<Role> roles = roleService.findByName("ROLE_CUSTOMER");
        user.setRoles(roles);
        Customer customer = modelMapper.map(dto, Customer.class);
        customer.setUser(user);
        return customer;
    }

    public CompanyAdmin dtoToCompanyAdmin(CompanyAdminDto dto) {
        User user = modelMapper.map(dto, User.class);
        List<Role> roles = roleService.findByName("ROLE_COMPANYADMIN");
        user.setRoles(roles);

        CompanyAdmin companyAdmin = modelMapper.map(user, CompanyAdmin.class);

        Company company = companyService.getById(dto.getCompanyId());

        companyAdmin.setCompany(company);

        return companyAdmin;
    }

    public UserDto mapUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User mapDtoToSystemAdmin(UserDto userDto) {
        User systemAdmin = modelMapper.map(userDto, User.class);
        List<Role> roles = roleService.findByName("ROLE_ADMIN");
        systemAdmin.setRoles(roles);
        return systemAdmin;
    }
}

