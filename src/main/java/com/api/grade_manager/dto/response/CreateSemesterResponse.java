package com.api.grade_manager.dto.response;

public class CreateSemesterResponse {

    private String nome;

    public CreateSemesterResponse() {
    }

    public CreateSemesterResponse(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
