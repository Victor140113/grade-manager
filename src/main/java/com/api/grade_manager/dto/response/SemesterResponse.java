package com.api.grade_manager.dto.response;

public class SemesterResponse {

    private String name;
    private Long id;
    private Integer courseQuantity;

    public SemesterResponse(String name, Long id, Integer courseQuantity) {
        this.name = name;
        this.id = id;
        this.courseQuantity = courseQuantity;
    }

    public Integer getCourseQuantity() {
        return courseQuantity;
    }

    public void setCourseQuantity(Integer courseQuantity) {
        this.courseQuantity = courseQuantity;
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
