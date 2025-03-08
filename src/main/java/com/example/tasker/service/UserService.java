package com.example.tasker.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.tasker.repository.UserRepository;
import com.example.tasker.model.UserEntity;

@Service
public class UserService {
    
    @Autowired 
    private UserRepository userRepository;

    public UserEntity getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
