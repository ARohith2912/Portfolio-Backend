package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String techStack;
    private String githubUrl;
    private String liveUrl;
    private String imageUrl;

    // 🔗 Relationship with User
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;
}