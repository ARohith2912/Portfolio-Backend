package com.example.demo.service.impl;

import com.example.demo.dto.SkillDTO;
import com.example.demo.entity.Skill;
import com.example.demo.entity.User;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.SkillService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository repo;
    private final UserRepository userRepo;

    public SkillServiceImpl(SkillRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public SkillDTO create(SkillDTO dto) {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Skill skill = mapToEntity(dto);
        skill.setUser(user);

        return mapToDTO(repo.save(skill));
    }

    @Override
    public List<SkillDTO> getByUser(Long userId) {
        return repo.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SkillDTO update(Long id, SkillDTO dto) {

        Skill skill = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        skill.setName(dto.getName());
        skill.setLevel(dto.getLevel());
        skill.setCategory(dto.getCategory());
        skill.setIcon(dto.getIcon());

        return mapToDTO(repo.save(skill));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // 🔁 MAPPERS

    private SkillDTO mapToDTO(Skill s) {
        return SkillDTO.builder()
               
                .name(s.getName())
                .level(s.getLevel())
                .category(s.getCategory())
                .icon(s.getIcon())
                .userId(s.getUser().getId())
                .build();
    }

    private Skill mapToEntity(SkillDTO dto) {
        return Skill.builder()
              
                .name(dto.getName())
                .level(dto.getLevel())
                .category(dto.getCategory())
                .icon(dto.getIcon())
                .build();
    }
}