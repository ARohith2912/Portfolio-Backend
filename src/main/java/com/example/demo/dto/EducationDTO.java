package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationDTO {

    private Long id;
    private String institution;
    private String degree;
    private String fieldOfStudy;
    private String startYear;
    private String endYear;
    private String grade;

    private Long userId;
}