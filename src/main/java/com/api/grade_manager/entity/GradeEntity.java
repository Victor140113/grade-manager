package com.api.grade_manager.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grade")
public class GradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;
    private Integer bim;

    @OneToMany(mappedBy = "grade")
    private List<GradeUpdateEntity> updates = new ArrayList<>();

    @ManyToOne
    private CourseEntity course;

    public GradeEntity() {
    }

    public GradeEntity(Double valor, List<GradeUpdateEntity> updates, Integer bim) {
        this.valor = valor;
        this.updates = updates;
        this.bim = bim;
    }

    public GradeEntity(Double valor, Integer bim) {
            this.valor = valor;
            this.bim = bim;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<GradeUpdateEntity> getUpdates() {
        return updates;
    }

    public Integer getBim() {
        return bim;
    }

    public void setBim(Integer bim) {
        this.bim = bim;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity courseEntity) {
        this.course = courseEntity;
    }

    public void setUpdates(List<GradeUpdateEntity> updates) {
        this.updates = updates;
    }
}
