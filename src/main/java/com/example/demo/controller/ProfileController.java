package com.example.demo.controller;

import com.example.demo.dto.ProfileDTO;
import com.example.demo.service.ProfileService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")

public class ProfileController {

    private final EducationController educationController;

    private final ProfileService service;

    public ProfileController(ProfileService service, EducationController educationController) {
        this.service = service;
        this.educationController = educationController;
    }

    @PostMapping
    public ProfileDTO save(@RequestBody ProfileDTO dto) {
        return service.save(dto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileDTO> get(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getByUser(userId));
    }
       
        
    
}