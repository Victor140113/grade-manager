package com.api.grade_manager.repository;

import com.api.grade_manager.entity.GradeUpdateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeUpdateRepository extends JpaRepository<GradeUpdateEntity, Long> {

    public GradeUpdateEntity findByIdAndGradeId(Long id, Long gradeId);
}
