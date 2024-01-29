package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/customer")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService _customerService;


    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer customerId){
        Customer customer = _customerService.getById(customerId);
        CustomerDto customerDto = new CustomerDto(customer.getUser(), customer.getOccupation(), customer.getCompanyInfo(), customer.getPenaltyPoints());
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerDto customerDto) {
        Customer customer = _customerService.updateCustomer(customerDto);
        if (customer != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}