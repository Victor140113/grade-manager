package com.api.grade_manager.service;

import com.api.grade_manager.dto.request.CreateGMRequest;
import com.api.grade_manager.dto.response.CreateGMResponse;
import com.api.grade_manager.entity.GradeManagerEntity;
import com.api.grade_manager.repository.GradeManagerRepository;
import org.springframework.stereotype.Service;

@Service
public class GradeManagerService {

    private final GradeManagerRepository database;

    public GradeManagerService(GradeManagerRepository database) {
        this.database = database;
    }

    // Métodos Externos


    // Criar Gerenciador de Notas
    public CreateGMResponse createGM(CreateGMRequest data){

        GradeManagerEntity gm = new GradeManagerEntity();
        gm.setNome(data.getNome());
        database.save(gm);

        return new CreateGMResponse(gm.getNome().trim());
    }

    // Métodos Internos

    public GradeManagerEntity getEntityById(Long id){

        return database.findById(id).orElse(null);
    }

}
