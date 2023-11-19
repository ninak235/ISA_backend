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

        return new CustomerDto(customer.getUser(),dto.getOccupation(),dto.getCompanyInfo(), dto.getPenaltyPoints());
    }

    public User getByEmail(String email){
        return _userRepository.findByEmailIgnoreCase(email);
    }

    public void updateUser(User user){
        _userRepository.save(user);
    }

    public Customer updateCustomer(CustomerDto customerDto) {
        Customer customer = _userMapper.dtoToCustomer(customerDto);

        User user = _userRepository.findByEmailIgnoreCase(customerDto.getEmail());
        user.setFirstName(customerDto.getFirstName());
        user.setLastName(customerDto.getLastName());
        user.setPassword(customerDto.getPassword());
        user.setCountry(customerDto.getCountry());
        user.setCity(customerDto.getCity());
        user.setNumber(customerDto.getNumber());

        Customer updatedCustomer = _customerRepository.findByUser_Email(customer.getUser().getEmail());
        updatedCustomer.setOccupation(customer.getOccupation());
        updatedCustomer.setCompanyInfo(customer.getCompanyInfo());
        updatedCustomer.setUser(user);
        _customerRepository.save(updatedCustomer);

        return updatedCustomer;
    }

    public Customer getById(Integer customerId) {
        return _customerRepository.findById(customerId).orElse(null);
    }

}
