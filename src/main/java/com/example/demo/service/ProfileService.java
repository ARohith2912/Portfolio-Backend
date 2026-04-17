package com.example.demo.service;

import com.example.demo.dto.ProfileDTO;

public interface ProfileService {
    ProfileDTO save(ProfileDTO dto);
    ProfileDTO getByUser(Long userId);
}