package com.api.grade_manager.repository;

import com.api.grade_manager.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    public CourseEntity findByIdAndSemesterId(Long id, Long semesterId);
    public List<CourseEntity> findAllBySemesterId(Long semesterId);
}
