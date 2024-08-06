package com.example.backend.models;

import com.example.backend.ProjectStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "status", nullable = false)
    private ProjectStatus status;

    @Column(name = "repository_url", nullable = false)
    private String repository_url;

    @Column(name = "project_url", nullable = false)
    private String project_url;

    @Column(name = "created_at", nullable = false)
    private String created_at;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Technology.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "projects_technologies", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "techonology_id"))
    private List<Technology> technologyList;
}
