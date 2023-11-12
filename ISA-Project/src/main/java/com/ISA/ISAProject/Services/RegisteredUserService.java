package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.UserRegistrationDto;
import com.ISA.ISAProject.Mapper.UserMapper;
import com.ISA.ISAProject.Model.RegisteredUser;
import com.ISA.ISAProject.Repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisteredUserService {

    @Autowired
    private RegisteredUserRepository _userRepository;
    @Autowired
    private final UserMapper _userMapper;


    public RegisteredUserService(UserMapper userMapper){
        _userMapper = userMapper;
    }

    public UserRegistrationDto registerUser(UserRegistrationDto dto){
        RegisteredUser registeredUser = _userMapper.dtoToRegisteredUser(dto);
        _userRepository.save(registeredUser);
        return new UserRegistrationDto(registeredUser.getUser(),dto.getOccupation(),dto.getCompanyInfo());
    }
}
