package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Enum.TypeOfUser;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper){this.modelMapper = modelMapper;}

    public Customer dtoToCustomer(CustomerDto dto){
        User user = modelMapper.map(dto,User.class);
        user.setTypeOfUser(TypeOfUser.Customer);
        Customer customer = modelMapper.map(dto, Customer.class);
        customer.setUser(user);
        return customer;
    }
}
