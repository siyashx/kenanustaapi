package com.codesupreme.kenanustaapi.model.elan;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "elans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Elan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(name = "image_path", nullable = false, length = 255)
    private String imagePath;

    @Column(nullable = false, length = 255, unique = true)
    private String slug;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}

