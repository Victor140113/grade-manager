package com.api.grade_manager.dto.response;

public class CreateGMResponse {

    private String name;

    public CreateGMResponse() {
    }

    public CreateGMResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
