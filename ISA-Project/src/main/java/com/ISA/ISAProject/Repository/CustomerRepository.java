package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByUser_Email(String email);
}
