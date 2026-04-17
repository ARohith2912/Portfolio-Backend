package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificationDTO {

    private Long id;
    private String title;
    private String issuer;
    private String issueDate;
    private String certificateUrl;
}