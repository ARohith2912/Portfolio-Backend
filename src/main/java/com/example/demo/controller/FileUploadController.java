package com.example.demo.controller;

import com.example.demo.dto.ResumeDTO;
import com.example.demo.service.ResumeService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
public class FileUploadController {

    private final ResumeService service;

    public FileUploadController(ResumeService service) {
        this.service = service;
    }

    @PostMapping("/upload/{userId}")
    public ResumeDTO upload(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file) {

        return service.uploadResume(userId, file);
    }

    @GetMapping("/user/{userId}")
    public ResumeDTO getResume(@PathVariable Long userId) {
        return service.getByUser(userId);
    }
}