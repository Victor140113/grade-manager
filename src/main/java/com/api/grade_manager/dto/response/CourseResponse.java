package com.api.grade_manager.dto.response;

import java.util.List;

public class CourseResponse {

    private Long id;
    private String name;
    private List<GradeResponse> gradeResponses;

    public CourseResponse(Long id, String name, List<GradeResponse> gradeResponses) {
        this.id = id;
        this.name = name;
        this.gradeResponses = gradeResponses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GradeResponse> getGradeResponses() {
        return gradeResponses;
    }

    public void setGradeResponses(List<GradeResponse> gradeResponses) {
        this.gradeResponses = gradeResponses;
    }
}
