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

    private Double value;
    private Integer bim;

    @OneToMany(mappedBy = "grade")
    private List<GradeUpdateEntity> updates = new ArrayList<>();

    @ManyToOne
    private CourseEntity course;

    public GradeEntity() {
    }

    public GradeEntity(Double value, List<GradeUpdateEntity> updates, Integer bim) {
        this.value = value;
        this.updates = updates;
        this.bim = bim;
    }

    public GradeEntity(Double value, Integer bim) {
            this.value = value;
            this.bim = bim;
    }

    public void addValue(GradeUpdateEntity update){

        if(this.value == null) this.value = 0.0;

        this.value += update.getValue();
    }

    public void subtractValue(GradeUpdateEntity update){
        this.value -= update.getValue();
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
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
