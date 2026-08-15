package com.api.grade_manager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "grade_update")
public class GradeUpdateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double value;

    @ManyToOne
    private GradeEntity grade;

    public GradeUpdateEntity() {
    }

    public GradeUpdateEntity(String description, Double value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public GradeEntity getGrade() {
        return grade;
    }

    public void setGrade(GradeEntity gradeEntity) {
        this.grade = gradeEntity;
    }
}
