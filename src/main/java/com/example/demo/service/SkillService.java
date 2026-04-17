package com.example.demo.service;

import com.example.demo.dto.SkillDTO;

import java.util.List;

public interface SkillService {

    SkillDTO create(SkillDTO dto);

    List<SkillDTO> getByUser(Long userId);

    SkillDTO update(Long id, SkillDTO dto);

    void delete(Long id);
}