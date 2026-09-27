package com.api.grade_manager.dto.request;

public class CreateGMRequest {

    private String name;

    public CreateGMRequest() {
    }

    public CreateGMRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
