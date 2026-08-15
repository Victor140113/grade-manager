package com.api.grade_manager.repository;

import com.api.grade_manager.entity.SemesterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<SemesterEntity, Long> {

    public SemesterEntity findByIdAndGradeManagerId(Long gmId, Long semesterId);
}
