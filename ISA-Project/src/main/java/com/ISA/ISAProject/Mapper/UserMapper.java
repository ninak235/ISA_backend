package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.*;
import com.ISA.ISAProject.Dto.CompanyAdminDto;
import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Model.Equipment;
import com.ISA.ISAProject.Model.Role;
import com.ISA.ISAProject.Model.User;
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
        user.setId(null);

        CompanyAdmin companyAdmin = modelMapper.map(user, CompanyAdmin.class);
        companyAdmin.getCompany().setId(dto.getCompanyId());

        return companyAdmin;
    }

    public UserDto mapUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User mapDtoToSystemAdmin(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}

