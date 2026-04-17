package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String level;      // Beginner / Intermediate / Expert
    private String category;   // Frontend / Backend / DevOps
    private String icon;       // icon URL or class name

    // 🔗 Relationship
    @ManyToOne
    @JsonIgnore 
    @JoinColumn(name = "user_id")
    private User user;
}

