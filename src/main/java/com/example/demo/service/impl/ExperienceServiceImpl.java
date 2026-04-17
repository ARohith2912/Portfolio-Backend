package com.example.demo.service.impl;

import com.example.demo.dto.ExperienceDTO;
import com.example.demo.entity.Experience;
import com.example.demo.entity.User;
import com.example.demo.repository.ExperienceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ExperienceService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository repo;
    private final UserRepository userRepo;

    public ExperienceServiceImpl(ExperienceRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public ExperienceDTO create(ExperienceDTO dto) {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Experience exp = mapToEntity(dto);
        exp.setUser(user);

        return mapToDTO(repo.save(exp));
    }

    @Override
    public List<ExperienceDTO> getByUser(Long userId) {
        return repo.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExperienceDTO update(Long id, ExperienceDTO dto) {

        Experience exp = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));

        exp.setCompany(dto.getCompany());
        exp.setRole(dto.getRole());
        exp.setStartDate(dto.getStartDate());
        exp.setEndDate(dto.getEndDate());
        exp.setDescription(dto.getDescription());

        return mapToDTO(repo.save(exp));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // 🔁 MAPPERS

    private ExperienceDTO mapToDTO(Experience e) {
        return ExperienceDTO.builder()
                .id(e.getId())
                .company(e.getCompany())
                .role(e.getRole())
                .startDate(e.getStartDate())
                .endDate(e.getEndDate())
                .description(e.getDescription())
                .userId(e.getUser().getId())
                .build();
    }

    private Experience mapToEntity(ExperienceDTO dto) {
        return Experience.builder()
                .id(dto.getId())
                .company(dto.getCompany())
                .role(dto.getRole())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .description(dto.getDescription())
                .build();
    }
}