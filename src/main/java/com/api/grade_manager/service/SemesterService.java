package com.api.grade_manager.service;

import com.api.grade_manager.dto.request.CreateSemesterRequest;
import com.api.grade_manager.dto.response.CreateSemesterResponse;
import com.api.grade_manager.entity.GradeManagerEntity;
import com.api.grade_manager.entity.SemesterEntity;
import com.api.grade_manager.exception.GMNotFoundException;
import com.api.grade_manager.exception.SemesterNotFoundException;
import com.api.grade_manager.repository.SemesterRepository;
import org.springframework.stereotype.Service;

@Service
public class SemesterService {

    private final SemesterRepository database;

    private final GradeManagerService gmService;

    public SemesterService(SemesterRepository database, GradeManagerService gmService) {
        this.database = database;
        this.gmService = gmService;
    }

    // Métodos Externos


    // Criar Semestre
    public CreateSemesterResponse createSemester(CreateSemesterRequest data, Long id){

        GradeManagerEntity gm = gmService.getEntityById(id);
        if (gm == null) throw new GMNotFoundException(" Grade Manager não encontrado!");

        SemesterEntity semester = new SemesterEntity();
        semester.setNome(data.getNome().trim());
        semester.setGm(gm);
        gm.getSemestre().add(semester);

        database.save(semester);

        return new CreateSemesterResponse(semester.getNome().trim());

    }

    public void deleteSemester(Long gmId, Long semesterId){

        GradeManagerEntity gm = gmService.getEntityById(gmId);
        if(gm == null) throw new GMNotFoundException(" Gerenciador de Notas não encontrado!");

        SemesterEntity semester = database.findByIdAndGradeManagerId(gm.getId(), semesterId);
        if(semester == null) throw new SemesterNotFoundException(" Semestre não encontrado");

        gm.getSemestre().remove(semester);

        database.delete(semester);
    }

    // Métodos Internos

    public SemesterEntity getSemesterById(Long id){
        return database.findById(id).orElse(null);
    }

}
