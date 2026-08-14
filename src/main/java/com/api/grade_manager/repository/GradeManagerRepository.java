package com.api.grade_manager.repository;

import com.api.grade_manager.entity.GradeManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeManagerRepository extends JpaRepository<GradeManagerEntity, Long> {

}
