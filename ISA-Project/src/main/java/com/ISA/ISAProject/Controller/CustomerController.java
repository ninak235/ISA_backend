package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Model.User;
import com.ISA.ISAProject.Services.EmailService;
import com.ISA.ISAProject.Services.CustomerService;
import com.ISA.ISAProject.Services.TokenService;
import com.ISA.ISAProject.Token.AccountConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/customer")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService _customerService;
    @Autowired
    private EmailService _emailService;
    @Autowired
    private TokenService _tokenService;

    @PostMapping(value = "/registerCustomer", consumes = "application/json")
    public ResponseEntity<Void> registerCustomer(@Valid @RequestBody CustomerDto registrationDto) {
        try {
            if (_emailService.isEmailUnique(registrationDto.getEmail())) {
                CustomerDto newUser = _customerService.registerCustomer(registrationDto);

                if (newUser == null) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                _emailService.sendEmail(registrationDto.getEmail());
                return new ResponseEntity<>( HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/confirm-account")
    public ResponseEntity<String>confirmUserAccount(@RequestParam("token") String confirmationToken){

        AccountConfirmationToken token = _tokenService.getConfirmationToken(confirmationToken);
        if (token != null) {
            User user = _customerService.getByEmail(token.getUser().getEmail());

            if (user != null) {
                user.setEnabled(true);
                _customerService.updateUser(user);
                return new ResponseEntity<>("Account successfully confirmed.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found for the provided token.", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid or expired token.", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer customerId){
        Customer customer = _customerService.getById(customerId);
        CustomerDto customerDto = new CustomerDto(customer.getUser(), customer.getOccupation(), customer.getCompanyInfo());
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerDto customerDto) {
        Customer customer = _customerService.updateCustomer(customerDto);
        if (customer != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}