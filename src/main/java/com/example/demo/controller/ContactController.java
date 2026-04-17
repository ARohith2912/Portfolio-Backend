package com.example.demo.controller;

import com.example.demo.dto.ContactDTO;
import com.example.demo.service.ContactService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @PostMapping
    public ContactDTO send(@RequestBody ContactDTO dto) {
        return service.save(dto);
    }
}