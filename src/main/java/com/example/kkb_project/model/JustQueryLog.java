package com.example.kkb_project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "just_querylog", indexes = {@Index(name = "idx_t", columnList = "T")})
public class JustQueryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime t;
    private String qh;
    private String qt;
    private String qc;
    private String cp;
    private String upstream;
    private String answer;
    private String ip;
    private String result;
    private String elapsed;
}