package com.api.grade_manager.dto.response;

import com.api.grade_manager.entity.GradeEntity;

import java.util.List;

public class CourseResponse {

    private String name;
    private Double nota1Bim;
    private Double nota2Bim;

    public CourseResponse(String name, Double nota1Bim, Double nota2Bim) {
        this.name = name;
        this.nota1Bim = nota1Bim;
        this.nota2Bim = nota2Bim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNota1Bim() {
        return nota1Bim;
    }

    public void setNota1Bim(Double nota1Bim) {
        this.nota1Bim = nota1Bim;
    }

    public Double getNota2Bim() {
        return nota2Bim;
    }

    public void setNota2Bim(Double nota2Bim) {
        this.nota2Bim = nota2Bim;
    }
}
