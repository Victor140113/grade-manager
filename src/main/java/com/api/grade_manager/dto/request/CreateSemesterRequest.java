package com.api.grade_manager.dto.request;

public class CreateSemesterRequest {

    private String name;

    public CreateSemesterRequest() {
    }

    public CreateSemesterRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
