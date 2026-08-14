package com.api.grade_manager.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "semester")
public class SemesterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private GradeManagerEntity gm;

    @OneToMany(mappedBy = "semester", fetch = FetchType.EAGER)
    private List<CourseEntity> course = new ArrayList<>();

    public SemesterEntity() {
    }

    public SemesterEntity(String nome, List<CourseEntity> course) {
        this.nome = nome;
        this.course = course;
    }

    public GradeManagerEntity getGm() {
        return gm;
    }

    public void setGm(GradeManagerEntity gm) {
        this.gm = gm;
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

    public List<CourseEntity> getCourse() {
        return course;
    }

    public void setCourse(List<CourseEntity> course) {
        this.course = course;
    }
}
