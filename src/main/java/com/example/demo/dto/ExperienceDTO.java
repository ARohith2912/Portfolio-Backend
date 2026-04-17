package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExperienceDTO {

    private Long id;
    private String company;
    private String role;
    private String startDate;
    private String endDate;
    private String description;

    private Long userId;
}