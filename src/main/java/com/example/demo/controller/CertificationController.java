package com.example.demo.controller;

import com.example.demo.dto.CertificationDTO;
import com.example.demo.service.CertificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    private final CertificationService service;

    public CertificationController(CertificationService service) {
        this.service = service;
    }

    @PostMapping
    public CertificationDTO create(@RequestBody CertificationDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<CertificationDTO> getAll() {
        return service.getAll();
    }
    

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}