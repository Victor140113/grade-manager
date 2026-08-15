package com.api.grade_manager.repository;

import com.api.grade_manager.entity.SemesterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterRepository extends JpaRepository<SemesterEntity, Long> {

    public SemesterEntity findByIdAndGmId(Long semesterId, Long gmId);
    public List<SemesterEntity> findAllByGmId(Long gmId);
}
