package com.example.demo.service.impl;

import com.example.demo.dto.EducationDTO;
import com.example.demo.entity.Education;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EducationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EducationService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository repo;
    private final UserRepository userRepo;

    public EducationServiceImpl(EducationRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public EducationDTO create(EducationDTO dto) {

        User user = userRepo.findById(dto.getUserId())
        		.orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Education edu = mapToEntity(dto);
        edu.setUser(user);

        return mapToDTO(repo.save(edu));
    }

    @Override
    public List<EducationDTO> getByUser(Long userId) {
        return repo.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EducationDTO update(Long id, EducationDTO dto) {

        Education edu = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Education not found"));

        edu.setInstitution(dto.getInstitution());
        edu.setDegree(dto.getDegree());
        edu.setFieldOfStudy(dto.getFieldOfStudy());
        edu.setStartYear(dto.getStartYear());
        edu.setEndYear(dto.getEndYear());
        edu.setGrade(dto.getGrade());

        return mapToDTO(repo.save(edu));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // 🔁 MAPPERS

    private EducationDTO mapToDTO(Education e) {
        return EducationDTO.builder()
                .id(e.getId())
                .institution(e.getInstitution())
                .degree(e.getDegree())
                .fieldOfStudy(e.getFieldOfStudy())
                .startYear(e.getStartYear())
                .endYear(e.getEndYear())
                .grade(e.getGrade())
                .userId(e.getUser().getId())
                .build();
    }

    private Education mapToEntity(EducationDTO dto) {
        return Education.builder()
                .id(dto.getId())
                .institution(dto.getInstitution())
                .degree(dto.getDegree())
                .fieldOfStudy(dto.getFieldOfStudy())
                .startYear(dto.getStartYear())
                .endYear(dto.getEndYear())
                .grade(dto.getGrade())
                .build();
    }
}