package com.api.grade_manager.dto.response;

public class CreateCourseResponse {

    private String name;

    public CreateCourseResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
