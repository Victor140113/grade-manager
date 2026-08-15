package com.api.grade_manager.service;

import com.api.grade_manager.dto.request.CreateGURequest;
import com.api.grade_manager.dto.request.PutGURequest;
import com.api.grade_manager.dto.response.CreateGUResponse;
import com.api.grade_manager.dto.response.GradeUpdateResponse;
import com.api.grade_manager.dto.response.PutGUResponse;
import com.api.grade_manager.entity.GradeEntity;
import com.api.grade_manager.entity.GradeUpdateEntity;
import com.api.grade_manager.exception.GUNotFoundException;
import com.api.grade_manager.exception.GradeNotFoundException;
import com.api.grade_manager.repository.GradeUpdateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

        GradeUpdateEntity gu = database.findByIdAndGradeId(guId, gradeId);
        if(gu == null) throw new GUNotFoundException(" O Update de Nota não foi encontrado!");

        grade.subtractValue(gu);
        grade.getUpdates().remove(gu);
        gradeService.saveGradeValues(grade);

        database.delete(gu);
    }

    public List<GradeUpdateResponse> getGUList(Long gradeId){

        return database.findAllByGradeId(gradeId).stream().map(gradeUpdate -> new GradeUpdateResponse(gradeUpdate.getDescription(), gradeUpdate.getValue())).toList();
    }

    public PutGUResponse putGradeUpdate(PutGURequest data, Long guId, Long gradeId){

        GradeEntity grade = gradeService.getGradeEntityById(gradeId);
        if(grade == null) throw new GradeNotFoundException(" Nota não encontrada!");

        GradeUpdateEntity gu = database.findByIdAndGradeId(guId, grade.getId());
        if(gu == null) throw new GUNotFoundException(" A Atualização de Nota não foi encontrada!");

        grade.subtractValue(gu);


        gu.setDescription(data.getDescription());
        gu.setValue(data.getValue());

        grade.addValue(gu);

        gradeService.saveGradeValues(grade);
        database.save(gu);

        return new PutGUResponse(gu.getDescription(), gu.getValue());
    }
}
