package com.api.grade_manager.dto.response;

public class CreateSemesterResponse {

    private String name;

    public CreateSemesterResponse() {
    }

    public CreateSemesterResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
