package com.api.grade_manager.dto.request;

public class CreateSemesterRequest {

    private String nome;

    public CreateSemesterRequest() {
    }

    public CreateSemesterRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
