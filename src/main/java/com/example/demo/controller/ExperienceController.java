package com.example.demo.controller;

import com.example.demo.dto.ExperienceDTO;
import com.example.demo.service.ExperienceService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience")

public class ExperienceController {

    private final ExperienceService service;

    public ExperienceController(ExperienceService service) {
        this.service = service;
    }

    @PostMapping
    public ExperienceDTO create(@RequestBody ExperienceDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/user/{userId}")
    public List<ExperienceDTO> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }

    @PutMapping("/{id}")
    public ExperienceDTO update(@PathVariable Long id, @RequestBody ExperienceDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted";
    }
}