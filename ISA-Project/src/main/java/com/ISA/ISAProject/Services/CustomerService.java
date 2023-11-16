package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Mapper.UserMapper;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Model.User;
import com.ISA.ISAProject.Repository.CustomerRepository;
import com.ISA.ISAProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository _customerRepository;
    @Autowired
    private final UserMapper _userMapper;
    @Autowired
    private UserRepository _userRepository;


    public CustomerService(UserMapper userMapper){
        _userMapper = userMapper;
    }

    public CustomerDto registerCustomer(CustomerDto dto){
        Customer customer = _userMapper.dtoToCustomer(dto);
        _customerRepository.save(customer);

        return new CustomerDto(customer.getUser(),dto.getOccupation(),dto.getCompanyInfo());
    }

    public User getByEmail(String email){
        return _userRepository.findByEmailIgnoreCase(email);
    }

    public void updateUser(User user){
        _userRepository.save(user);
    }
}
