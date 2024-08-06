package com.example.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

import java.util.List;

@Data
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "photo")
    private String photo;

    @Column(name = "position", length = 100)
    private String position;

    @Column(name = "location", length = 200)
    private String location;

    @Column(name = "linkedin_url")
    private String linkedin_url;

    @Column(name = "github_url")
    private String github_url;

    @Column(name = "youtube_url")
    private String youtube_url;

    @Column(name = "x_url")
    private String x_url;

    @Column(name = "instagram_url")
    private String instagram_url;

    @Column(name = "curriculum_vitae")
    private String curriculum_vitae;

    @OneToMany
    @JoinColumn(name = "profile_id")
    private List<Project> projectList;
}
