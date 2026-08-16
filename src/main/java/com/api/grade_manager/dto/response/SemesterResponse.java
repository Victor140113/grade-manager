package com.api.grade_manager.dto.response;

public class SemesterResponse {

    private String name;

    public SemesterResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
