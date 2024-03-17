package com.example.kkb_project.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JustQueryLogRepository extends JpaRepository<JustQueryLog, Long> {
    List<JustQueryLog> findByTBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
