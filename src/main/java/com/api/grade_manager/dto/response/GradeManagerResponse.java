package com.api.grade_manager.dto.response;

public class GradeManagerResponse {

    private String nome;

    public GradeManagerResponse(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
