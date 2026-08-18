package com.api.grade_manager.dto.response;

public class GradeUpdateResponse {

    private Long id;
    private String description;
    private Double value;
    private Long gradeId;

    public GradeUpdateResponse(Long id, String description, Double value, Long gradeId) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.gradeId = gradeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }
}
