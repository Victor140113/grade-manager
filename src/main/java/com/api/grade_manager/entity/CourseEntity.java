package com.api.grade_manager.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<GradeEntity> grade = new ArrayList<>();

    @ManyToOne
    private SemesterEntity semester;

    public CourseEntity() {
    }

    public CourseEntity(String nome, List<GradeEntity> grade, SemesterEntity semester) {
        this.nome = nome;
        this.grade = grade;
        this.semester = semester;
    }

    public List<GradeEntity> getGrades() {
        return grade;
    }

    public void setGrades(List<GradeEntity> gradeEntities) {
        this.grade = gradeEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SemesterEntity getSemester() {
        return semester;
    }

    public void setSemester(SemesterEntity semesterEntity) {
        this.semester = semesterEntity;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
