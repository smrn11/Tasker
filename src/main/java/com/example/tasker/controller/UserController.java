package com.example.tasker.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.tasker.service.UserService;
import com.example.tasker.mapper.UserMapper;
import com.example.tasker.model.*;


@RestController
@RequestMapping("/tasker/user")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId){
        return userMapper.toDto(userService.getUser(userId));
    }

    @PostMapping
    public ResponseEntity<UserDto> createTask(@RequestBody UserDto userDto) {
        UserEntity newUserEntity = userMapper.toEntity(userDto);
        UserDto createdUserDto = userMapper.toDto(userService.createUser(newUserEntity));
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
