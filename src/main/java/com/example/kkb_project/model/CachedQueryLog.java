package com.example.kkb_project.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

@Entity
@Table(name = "cached_querylog")
public class CachedQueryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime t;
    private String qh;
    private String qt;
    private String qc;
    private String cp;
    private String answer;
    private String ip;
    private String result;
    private String elapsed;
    private boolean cached;
    // getter, setter 등 필요한 메서드 추가
}

