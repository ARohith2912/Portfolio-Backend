package com.example.demo.service.impl;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProjectService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepo;
    private final UserRepository userRepo;

    public ProjectServiceImpl(ProjectRepository projectRepo, UserRepository userRepo) {
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
    }

    @Override
    public ProjectDTO createProject(ProjectDTO dto) {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = mapToEntity(dto);
        project.setUser(user);

        return mapToDTO(projectRepo.save(project));
    }

    @Override
    public List<ProjectDTO> getProjectsByUser(Long userId) {
        return projectRepo.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO dto) {

        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setTechStack(dto.getTechStack());
        project.setGithubUrl(dto.getGithubUrl());
        project.setLiveUrl(dto.getLiveUrl());
        project.setImageUrl(dto.getImageUrl());

        return mapToDTO(projectRepo.save(project));
    }

    @Override
    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }

    // 🔁 MAPPER

    private ProjectDTO mapToDTO(Project p) {
        return ProjectDTO.builder()
                .id(p.getId())
                .title(p.getTitle())
                .description(p.getDescription())
                .techStack(p.getTechStack())
                .githubUrl(p.getGithubUrl())
                .liveUrl(p.getLiveUrl())
                .imageUrl(p.getImageUrl())
                .userId(p.getUser().getId())
                .build();
    }

    private Project mapToEntity(ProjectDTO dto) {
        return Project.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .techStack(dto.getTechStack())
                .githubUrl(dto.getGithubUrl())
                .liveUrl(dto.getLiveUrl())
                .imageUrl(dto.getImageUrl())
                .build();
    }
}