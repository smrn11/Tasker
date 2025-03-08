package com.example.tasker.mapper;

import com.example.tasker.model.UserDto;
import com.example.tasker.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public UserDto toDto(UserEntity user) {
        if (user == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        return dto;
    }

    public UserEntity toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        return user;
    }
}
