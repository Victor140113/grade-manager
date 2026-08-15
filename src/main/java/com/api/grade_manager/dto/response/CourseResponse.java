package com.api.grade_manager.dto.response;

import com.api.grade_manager.entity.GradeEntity;

import java.util.List;

public class CourseResponse {

    private String name;
    private List<GradeEntity> grades;

    public CourseResponse(String name, List<GradeEntity> grades) {
        this.name = name;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GradeEntity> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeEntity> grades) {
        this.grades = grades;
    }
}
