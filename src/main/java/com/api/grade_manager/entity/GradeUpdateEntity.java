package com.api.grade_manager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "grade_update")
public class GradeUpdateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Double valor;

    @ManyToOne
    private GradeEntity grade;

    public GradeUpdateEntity() {
    }

    public GradeUpdateEntity(String descricao, Double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public GradeEntity getGrade() {
        return grade;
    }

    public void setGrade(GradeEntity gradeEntity) {
        this.grade = gradeEntity;
    }
}
