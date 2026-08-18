package com.api.grade_manager.dto.response;

public class GradeManagerResponse {

    private String name;
    private Long id;
    private Integer semesterQuantity;

    public GradeManagerResponse(String name, Long id, Integer semesterQuantity) {
        this.name = name;
        this.id = id;
        this.semesterQuantity = semesterQuantity;
    }

    public Integer getSemesterQuantity() {
        return semesterQuantity;
    }

    public void setSemesterQuantity(Integer semesterQuantity) {
        this.semesterQuantity = semesterQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
