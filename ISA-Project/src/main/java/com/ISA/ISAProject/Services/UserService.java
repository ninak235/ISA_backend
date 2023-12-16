package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.CompanyAdminDto;
import com.ISA.ISAProject.Dto.UserDto;
import com.ISA.ISAProject.Mapper.UserMapper;
import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.User;
import com.ISA.ISAProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private final UserMapper _userMapper;

    public UserService(UserMapper userMapper) {
        _userMapper = userMapper;
    }

    public UserDto registerSystemAdmin(UserDto dto) {
        User systemAdmin = _userRepository.save(_userMapper.mapDtoToSystemAdmin(dto));
        return new UserDto(systemAdmin);
    }

    public User getById(Integer userId) {
        return _userRepository.findById(userId).orElse(null);
    }
}
