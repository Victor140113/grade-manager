package com.api.grade_manager.service;

import com.api.grade_manager.dto.request.CreateGURequest;
import com.api.grade_manager.dto.response.CreateGUResponse;
import com.api.grade_manager.dto.response.DeleteGUResponse;
import com.api.grade_manager.entity.GradeEntity;
import com.api.grade_manager.entity.GradeUpdateEntity;
import com.api.grade_manager.exception.GUNotFoundException;
import com.api.grade_manager.exception.GradeNotFoundException;
import com.api.grade_manager.repository.GradeUpdateRepository;
import org.springframework.stereotype.Service;

@Service
public class GradeUpdateService {

    private final GradeUpdateRepository database;
    private final GradeService gradeService;

    public GradeUpdateService(GradeUpdateRepository database, GradeService gradeService) {
        this.database = database;
        this.gradeService = gradeService;
    }

    // Métodos Externos

    public CreateGUResponse createGradeUpdate(CreateGURequest data, Long gradeId){

        GradeEntity grade = gradeService.getGradeEntityById(gradeId);
        if(grade == null) throw new GradeNotFoundException(" A nota não foi encontrada!");

        GradeUpdateEntity gu = new GradeUpdateEntity();
        gu.setDescription(data.getDescription());
        gu.setValue(data.getValue());
        gu.setGrade(grade);

        grade.getUpdates().add(gu);

        database.save(gu);

        grade.addValue(gu);
        gradeService.saveGradeValues(grade);

        return new CreateGUResponse(gu.getDescription(), gu.getValue());
    }

    public void deleteGradeUpdate(Long gradeId, Long guId){

        GradeEntity grade = gradeService.getGradeEntityById(gradeId);
        if(grade == null) throw new GradeNotFoundException(" A nota não foi encontrada!");

        GradeUpdateEntity gu = database.findById(guId).orElse(null);
        if(gu == null) throw new GUNotFoundException(" O Update de Nota não foi encontrado!");

        grade.subtractValue(gu);
        grade.getUpdates().remove(gu);
        gradeService.saveGradeValues(grade);

        database.delete(gu);
    }
}
