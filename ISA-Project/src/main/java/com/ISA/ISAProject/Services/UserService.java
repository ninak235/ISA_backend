package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Dto.UserDto;
import com.ISA.ISAProject.Mapper.UserMapper;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Model.User;
import com.ISA.ISAProject.Repository.CustomerRepository;
import com.ISA.ISAProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private CustomerRepository _customerRepository;
    @Autowired
    private final UserMapper _userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper) { _userMapper = userMapper;}

    public User findByUsername(String username) throws UsernameNotFoundException {
        return _userRepository.findByUserName(username);
    }

    public UserDto registerSystemAdmin(UserDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User systemAdmin = _userRepository.save(_userMapper.mapDtoToSystemAdmin(dto));
        return new UserDto(systemAdmin);
    }

    public User getById(Integer userId) {
        return _userRepository.findById(userId).orElse(null);
    }

    public User findById(Integer id) throws AccessDeniedException {
        return _userRepository.findById(id).orElseGet(null);
    }

    public List<User> findAll() throws AccessDeniedException {
        return _userRepository.findAll();
    }

    public CustomerDto registerCustomer(CustomerDto dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Customer customer = _userMapper.dtoToCustomer(dto);
        _customerRepository.save(customer);

        return new CustomerDto(customer.getUser(),dto.getOccupation(),dto.getCompanyInfo(), dto.getPenaltyPoints());
    }

}
