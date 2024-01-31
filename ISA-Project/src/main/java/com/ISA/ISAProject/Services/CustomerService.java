package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Mapper.UserMapper;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Model.LoyalityProgram;
import com.ISA.ISAProject.Model.User;
import com.ISA.ISAProject.Repository.CustomerRepository;
import com.ISA.ISAProject.Repository.LoyalityProgramRepository;
import com.ISA.ISAProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository _customerRepository;
    @Autowired
    private final UserMapper _userMapper;
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LoyalityProgramRepository _loyalityProgramRepository;


    public CustomerService(UserMapper userMapper){
        _userMapper = userMapper;
    }

    public CustomerDto registerCustomer(CustomerDto dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Customer customer = _userMapper.dtoToCustomer(dto);

        Optional<LoyalityProgram> loyalityProgramOptional = _loyalityProgramRepository.findById(3);
        if (loyalityProgramOptional.isPresent()) {
            LoyalityProgram loyalityProgram = loyalityProgramOptional.get();
            customer.setLoyalityProgram(loyalityProgram);
        }
        _customerRepository.save(customer);

        return new CustomerDto(customer.getUser(),dto.getOccupation(),dto.getCompanyInfo(), dto.getPenaltyPoints(), dto.getLoyalityProgramId());
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
        updatedCustomer.setPenaltyPoints(customer.getPenaltyPoints());
        updatedCustomer.setUser(user);
        _customerRepository.save(updatedCustomer);

        return updatedCustomer;
    }


    public void resetPenaltyPoints() {
        System.out.println("usao u reset");
        LocalDateTime currentDate = LocalDateTime.now();
        List<Customer> customers = _customerRepository.findAll();

        for (Customer customer : customers) {
            LocalDateTime lastResetDate = customer.getLastPenaltyPointsDateReset();

            if (lastResetDate == null || !currentDate.getMonth().equals(lastResetDate.getMonth())) {

                customer.setPenaltyPoints(0);
                customer.setLastPenaltyPointsDateReset(currentDate);
                _customerRepository.save(customer);
            }
        }
    }


    public Customer getById(Integer customerId) {
        resetPenaltyPoints();
        return _customerRepository.findById(customerId).orElse(null);
    }

}
