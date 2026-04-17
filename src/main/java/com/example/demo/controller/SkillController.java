package com.example.demo.controller;

import com.example.demo.dto.SkillDTO;
import com.example.demo.service.SkillService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:5173")
public class SkillController {

    private final SkillService service;

    public SkillController(SkillService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SkillDTO> create(@RequestBody SkillDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/user/{userId}")
    public List<SkillDTO> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }

    @PutMapping("/{id}")
    public SkillDTO update(@PathVariable Long id, @RequestBody SkillDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted";
    }
}