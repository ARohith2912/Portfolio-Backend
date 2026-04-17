package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDTO {

    private Long id;
    private String fileName;
    private String filePath;
    private Long userId;
}