package com.api.grade_manager.service;

import com.api.grade_manager.entity.CourseEntity;
import com.api.grade_manager.entity.GradeEntity;
import com.api.grade_manager.repository.GradeRepository;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    private final GradeRepository database;

    public GradeService(GradeRepository database) {
        this.database = database;
    }

    // Métodos Internos

    public void saveGradeOnCourse(GradeEntity grade, CourseEntity course){
        grade.setCourse(course);
        database.save(grade);
    }

    public void saveGradeValues(GradeEntity grade){
        database.save(grade);
    }

    public GradeEntity getGradeEntityById(Long id){
        return database.findById(id).orElse(null);
    }
}
