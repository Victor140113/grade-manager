package com.api.grade_manager.dto.response;

import java.util.List;

public class CourseResponse {

    private Long id;
    private String name;
    private List<GradeResponse> gradeResponses;
    private Long semesterId;

    public CourseResponse(Long id, String name, List<GradeResponse> gradeResponses, Long semesterId) {
        this.id = id;
        this.name = name;
        this.gradeResponses = gradeResponses;
        this.semesterId = semesterId;
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

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }
}
