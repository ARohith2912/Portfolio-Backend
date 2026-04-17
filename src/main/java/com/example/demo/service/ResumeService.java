package com.example.demo.service;

import com.example.demo.dto.ResumeDTO;

import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {

    ResumeDTO uploadResume(Long userId, MultipartFile file);

    ResumeDTO getByUser(Long userId);
}