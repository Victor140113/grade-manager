package com.api.grade_manager.dto.response;

public class GradeUpdateResponse {

    private String description;
    private Double value;

    public GradeUpdateResponse(String description, Double value) {
        this.description = description;
        this.value = value;
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
}
