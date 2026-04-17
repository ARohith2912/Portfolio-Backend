package com.example.demo.controller;

import com.example.demo.dto.EducationDTO;
import com.example.demo.service.EducationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService service;

    public EducationController(EducationService service) {
        this.service = service;
    }

    @PostMapping
    public EducationDTO create(@RequestBody EducationDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/user/{userId}")
    public List<EducationDTO> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }

    @PutMapping("/{id}")
    public EducationDTO update(@PathVariable Long id, @RequestBody EducationDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}