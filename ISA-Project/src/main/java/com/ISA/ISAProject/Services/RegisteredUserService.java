package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.UserRegistrationDto;
import com.ISA.ISAProject.Mapper.UserMapper;
import com.ISA.ISAProject.Model.RegisteredUser;
import com.ISA.ISAProject.Model.User;
import com.ISA.ISAProject.Repository.RegisteredUserRepository;
import com.ISA.ISAProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisteredUserService {

    @Autowired
    private RegisteredUserRepository _registeredUserRepository;
    @Autowired
    private final UserMapper _userMapper;
    @Autowired
    private UserRepository _userRepository;


    public RegisteredUserService(UserMapper userMapper){
        _userMapper = userMapper;
    }

    public UserRegistrationDto registerUser(UserRegistrationDto dto){
        RegisteredUser registeredUser = _userMapper.dtoToRegisteredUser(dto);
        _registeredUserRepository.save(registeredUser);

        return new UserRegistrationDto(registeredUser.getUser(),dto.getOccupation(),dto.getCompanyInfo());
    }

    public User getByEmail(String email){
        return _userRepository.findByEmailIgnoreCase(email);
    }

    public void updateUser(User user){
        _userRepository.save(user);
    }
}
