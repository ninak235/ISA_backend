package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.UserRegistrationDto;
import com.ISA.ISAProject.Enum.TypeOfUser;
import com.ISA.ISAProject.Model.RegisteredUser;
import com.ISA.ISAProject.Model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper){this.modelMapper = modelMapper;}

    public RegisteredUser dtoToRegisteredUser(UserRegistrationDto dto){
        User user = modelMapper.map(dto,User.class);
        user.setTypeOfUser(TypeOfUser.RegisteredUser);
        RegisteredUser registeredUser = modelMapper.map(dto, RegisteredUser.class);
        registeredUser.setUser(user);
        return registeredUser;
    }
}
