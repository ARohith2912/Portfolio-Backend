package com.example.demo.service;

import com.example.demo.dto.ExperienceDTO;

import java.util.List;

public interface ExperienceService {

    ExperienceDTO create(ExperienceDTO dto);

    List<ExperienceDTO> getByUser(Long userId);

    ExperienceDTO update(Long id, ExperienceDTO dto);

    void delete(Long id);
}