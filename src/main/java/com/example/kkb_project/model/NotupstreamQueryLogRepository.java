package com.example.kkb_project.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotupstreamQueryLogRepository extends JpaRepository<NotupstreamQueryLog, Long> {
    List<NotupstreamQueryLog> findByTBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
