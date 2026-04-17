package com.example.demo.service;

import com.example.demo.dto.CertificationDTO;
import java.util.List;

public interface CertificationService {
    CertificationDTO create(CertificationDTO dto);
    List<CertificationDTO> getAll();
    void delete(Long id);
}