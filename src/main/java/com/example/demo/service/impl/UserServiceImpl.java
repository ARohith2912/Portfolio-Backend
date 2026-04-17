package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = mapToEntity(dto);
        return mapToDTO(repository.save(user));
    }

    @Override
    public UserDTO getUser(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setBio(dto.getBio());
        user.setProfileImage(dto.getProfileImage());
        user.setResumeUrl(dto.getResumeUrl());

        return mapToDTO(repository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    // 🔁 MAPPERS

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .bio(user.getBio())
                .profileImage(user.getProfileImage())
                .resumeUrl(user.getResumeUrl())
                .build();
    }

    private User mapToEntity(UserDTO dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .role(dto.getRole())
                .bio(dto.getBio())
                .profileImage(dto.getProfileImage())
                .resumeUrl(dto.getResumeUrl())
                .build();
    }
}