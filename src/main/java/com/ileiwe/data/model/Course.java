package com.ileiwe.data.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author oluwatobi
 * @version 1.0
 * @date on 27/10/2021
 * inside the package - com.ileiwe.data.model
 */

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 1000)
    private String description;
    private String duration;
    private String language;
    @ElementCollection
    private List<String> imageUrls;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    private LocalDateTime datePublished;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private boolean isPublished;
    @ManyToOne
    private Instructor instructor;
    @ManyToMany
    private List<Student> students;
}
