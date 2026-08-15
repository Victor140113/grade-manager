package com.api.grade_manager.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grade_manager")
public class GradeManagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "gm", cascade = CascadeType.REMOVE)
    private List<SemesterEntity> semestre = new ArrayList<>();

    public GradeManagerEntity() {
    }

    public GradeManagerEntity(String nome, List<SemesterEntity> semestre) {
        this.nome = nome;
        this.semestre = semestre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<SemesterEntity> getSemestre() {
        return semestre;
    }

    public void setSemestre(List<SemesterEntity> semestre) {
        this.semestre = semestre;
    }
}