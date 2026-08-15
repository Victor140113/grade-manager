package com.api.grade_manager.dto.response;

public class CreateGUResponse {

    private String description;
    private Double value;

    public CreateGUResponse(String description, Double value) {
        this.description = description;
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
