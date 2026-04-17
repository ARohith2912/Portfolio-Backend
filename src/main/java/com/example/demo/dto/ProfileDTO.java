package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDTO {

    private Long id;
    private String name;
    private String title;
    private String bio;
    private String email;
    private String github;
    private String linkedin;
    private String resumeUrl;
    private String profileImage;
}