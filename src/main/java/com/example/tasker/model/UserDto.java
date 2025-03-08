package com.example.tasker.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;
    
    private String phone;
}
