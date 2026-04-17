package com.example.demo.controller;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.service.ProjectService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    public ProjectDTO createProject(@RequestBody ProjectDTO dto) {
        return service.createProject(dto);
    }

    @GetMapping("/user/{userId}")
    public List<ProjectDTO> getProjects(@PathVariable Long userId) {
        return service.getProjectsByUser(userId);
    }

    @PutMapping("/{id}")
    public ProjectDTO updateProject(@PathVariable Long id, @RequestBody ProjectDTO dto) {
        return service.updateProject(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable Long id) {
        service.deleteProject(id);
        return "Project deleted";
    }
}