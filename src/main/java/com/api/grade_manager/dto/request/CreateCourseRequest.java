package com.api.grade_manager.dto.request;

public class CreateCourseRequest {

    private String name;

    public CreateCourseRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
