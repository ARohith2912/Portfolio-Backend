package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillDTO {

 
    private String name;
    private String level;
    private String category;
    private String icon;

    private Long userId;
}