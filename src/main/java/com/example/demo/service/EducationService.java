package com.example.demo.service;

import com.example.demo.dto.EducationDTO;

import java.util.List;

public interface EducationService {

    EducationDTO create(EducationDTO dto);

    List<EducationDTO> getByUser(Long userId);

    EducationDTO update(Long id, EducationDTO dto);

    void delete(Long id);
}