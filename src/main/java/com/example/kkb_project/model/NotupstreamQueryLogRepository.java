package com.example.kkb_project.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotupstreamQueryLogRepository extends JpaRepository<NotupstreamQueryLog, Long> {
    // JustQueryLog 엔티티에 맞는 추가적인 메서드 정의
}
