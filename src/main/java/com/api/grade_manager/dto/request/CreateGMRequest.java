package com.api.grade_manager.dto.request;

public class CreateGMRequest {

    private String nome;

    public CreateGMRequest() {
    }

    public CreateGMRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
