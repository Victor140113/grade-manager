package com.api.grade_manager.service;

import com.api.grade_manager.dto.request.CreateGURequest;
import com.api.grade_manager.dto.response.CreateCourseResponse;
import com.api.grade_manager.dto.response.CreateGUResponse;
import com.api.grade_manager.entity.GradeEntity;
import com.api.grade_manager.entity.GradeUpdateEntity;
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
}
