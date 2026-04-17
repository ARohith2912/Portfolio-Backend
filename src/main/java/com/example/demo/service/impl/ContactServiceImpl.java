package com.example.demo.service.impl;

import com.example.demo.dto.ContactDTO;
import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repo;

    public ContactServiceImpl(ContactRepository repo) {
        this.repo = repo;
    }

    @Override
    public ContactDTO save(ContactDTO dto) {
        Contact contact = Contact.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .message(dto.getMessage())
                .build();

        contact = repo.save(contact);

        dto.setId(contact.getId());
        return dto;
    }
}