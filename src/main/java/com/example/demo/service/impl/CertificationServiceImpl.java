package com.example.demo.service.impl;

import com.example.demo.dto.CertificationDTO;
import com.example.demo.entity.Certification;
import com.example.demo.repository.CertificationRepository;
import com.example.demo.service.CertificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificationServiceImpl implements CertificationService {

    private final CertificationRepository repo;

    public CertificationServiceImpl(CertificationRepository repo) {
        this.repo = repo;
    }

    @Override
    public CertificationDTO create(CertificationDTO dto) {
        Certification cert = Certification.builder()
                .title(dto.getTitle())
                .issuer(dto.getIssuer())
                .issueDate(dto.getIssueDate())
                .certificateUrl(dto.getCertificateUrl())
                .build();

        return mapToDTO(repo.save(cert));
    }

    @Override
    public List<CertificationDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
    	if (!repo.existsById(id)) {
    	    throw new RuntimeException("Certification not found");
    	}
    	repo.deleteById(id);
    }

    private CertificationDTO mapToDTO(Certification cert) {
        return CertificationDTO.builder()
                .id(cert.getId())
                .title(cert.getTitle())
                .issuer(cert.getIssuer())
                .issueDate(cert.getIssueDate())
                .certificateUrl(cert.getCertificateUrl())
                .build();
    }
}