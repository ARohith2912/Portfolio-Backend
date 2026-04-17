package com.example.demo.service.impl;

import com.example.demo.dto.ProfileDTO;
import com.example.demo.entity.Profile;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.service.ProfileService;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository repo;

    public ProfileServiceImpl(ProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public ProfileDTO save(ProfileDTO dto) {

        Profile profile = Profile.builder()
                .id(dto.getId())
                .name(dto.getName())
                .title(dto.getTitle())
                .bio(dto.getBio())
                .email(dto.getEmail())
                .github(dto.getGithub())
                .linkedin(dto.getLinkedin())
                .resumeUrl(dto.getResumeUrl())
                .profileImage(dto.getProfileImage())
                .build();

        return mapToDTO(repo.save(profile));
    }

    @Override
    public ProfileDTO getByUser(Long userId) {
        return repo.findAll().stream().findFirst()
                .map(this::mapToDTO)
                .orElse(null);
    }

    private ProfileDTO mapToDTO(Profile p) {
        return ProfileDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .title(p.getTitle())
                .bio(p.getBio())
                .email(p.getEmail())
                .github(p.getGithub())
                .linkedin(p.getLinkedin())
                .resumeUrl(p.getResumeUrl())
                .profileImage(p.getProfileImage())
                .build();
    }
}