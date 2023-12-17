package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmailIgnoreCase(String email);
    User findByUserName(String userName);
}
