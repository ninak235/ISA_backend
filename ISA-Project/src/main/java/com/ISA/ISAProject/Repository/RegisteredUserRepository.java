package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser,Integer> {

}
