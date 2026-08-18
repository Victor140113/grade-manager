package com.api.grade_manager.dto.response;

import java.util.List;

public class GradeResponse {

    private Long id;
    private Double value;
    private Integer bim;
    private List<GradeUpdateResponse> gradeUpdates;

    public GradeResponse(Long id, Double value, Integer bim, List<GradeUpdateResponse> gradeUpdates) {
        this.id = id;
        this.value = value;
        this.bim = bim;
        this.gradeUpdates = gradeUpdates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getBim() {
        return bim;
    }

    public void setBim(Integer bim) {
        this.bim = bim;
    }

    public List<GradeUpdateResponse> getGradeUpdates() {
        return gradeUpdates;
    }

    public void setGradeUpdates(List<GradeUpdateResponse> gradeUpdates) {
        this.gradeUpdates = gradeUpdates;
    }
}
