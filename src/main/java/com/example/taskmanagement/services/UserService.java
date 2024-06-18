package com.example.taskmanagement.services;

import com.example.taskmanagement.dtos.AuthenticationResponse;
import com.example.taskmanagement.dtos.RegisterRequest;
import com.example.taskmanagement.dtos.UserDto;
import com.example.taskmanagement.models.User;

import java.util.List;


public interface UserService {
    AuthenticationResponse addUser(RegisterRequest req);

    UserDto updateUser(User newUser, Integer id);

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    void deleteUserById(Integer id);
}