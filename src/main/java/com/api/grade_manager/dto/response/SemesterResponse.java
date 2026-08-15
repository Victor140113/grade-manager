package com.api.grade_manager.dto.response;

public class SemesterResponse {

    private String nome;

    public SemesterResponse(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
