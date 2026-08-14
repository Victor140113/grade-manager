package com.api.grade_manager.dto.request;

public class CreateCourseRequest {

    private String nome;

    public CreateCourseRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
