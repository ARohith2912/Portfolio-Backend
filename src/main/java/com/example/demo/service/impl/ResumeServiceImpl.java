package com.example.demo.service.impl;

import com.example.demo.dto.ResumeDTO;
import com.example.demo.entity.Resume;
import com.example.demo.entity.User;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ResumeService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository repo;
    private final UserRepository userRepo;

    public ResumeServiceImpl(ResumeRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public ResumeDTO uploadResume(Long userId, MultipartFile file) {

        try {
            String path = "uploads/" + file.getOriginalFilename();

            File dest = new File(path);
            dest.getParentFile().mkdirs();
            file.transferTo(dest);

            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Resume resume = repo.findByUserId(userId).orElse(new Resume());

            resume.setFileName(file.getOriginalFilename());
            resume.setFilePath(path);
            resume.setUser(user);

            return mapToDTO(repo.save(resume));

        } catch (Exception e) {
            throw new RuntimeException("Upload failed");
        }
    }

    @Override
    public ResumeDTO getByUser(Long userId) {
        Resume resume = repo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        return mapToDTO(resume);
    }

    private ResumeDTO mapToDTO(Resume r) {
        return ResumeDTO.builder()
                .id(r.getId())
                .fileName(r.getFileName())
                .filePath(r.getFilePath())
                .userId(r.getUser().getId())
                .build();
    }
}