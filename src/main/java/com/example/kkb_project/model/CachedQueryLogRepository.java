package com.example.kkb_project.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CachedQueryLogRepository extends JpaRepository<CachedQueryLog, Long> {
    List<CachedQueryLog> findByTBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
