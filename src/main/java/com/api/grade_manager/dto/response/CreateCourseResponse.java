package com.api.grade_manager.dto.response;

public class CreateCourseResponse {

    private String nome;

    public CreateCourseResponse(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
