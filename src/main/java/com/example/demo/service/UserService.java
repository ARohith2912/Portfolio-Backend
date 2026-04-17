package com.example.demo.service;

import com.example.demo.dto.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO dto);

    UserDTO getUser(Long id);

    UserDTO updateUser(Long id, UserDTO dto);

    void deleteUser(Long id);
}