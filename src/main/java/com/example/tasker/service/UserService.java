package com.example.tasker.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.tasker.repository.UserRepository;
import com.example.tasker.mapper.UserMapper;
import com.example.tasker.model.UserDto;
import com.example.tasker.model.UserEntity;

@Service
public class UserService {
    
    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDto getUser(Long userId) {
        return userMapper.toDto(userRepository.findById(userId).orElse(null));
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
            .map(userMapper::toDto)
            .collect(Collectors.toList());
    }

    public UserDto createUser(UserDto userDto) {
        UserEntity newUser = userMapper.toEntity(userDto);
        userRepository.save(newUser);
        return userDto;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
