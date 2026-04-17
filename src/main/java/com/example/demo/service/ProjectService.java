package com.example.demo.service;

import com.example.demo.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO createProject(ProjectDTO dto);

    List<ProjectDTO> getProjectsByUser(Long userId);

    ProjectDTO updateProject(Long id, ProjectDTO dto);

    void deleteProject(Long id);
}