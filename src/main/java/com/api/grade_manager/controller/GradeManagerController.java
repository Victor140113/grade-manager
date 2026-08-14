package com.api.grade_manager.controller;

import com.api.grade_manager.dto.request.CreateGMRequest;
import com.api.grade_manager.dto.response.CreateGMResponse;
import com.api.grade_manager.service.GradeManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GradeManagerController {

    private final GradeManagerService service;

    public GradeManagerController(GradeManagerService service) {
        this.service = service;
    }

    @PostMapping("/grade-manager")
    public ResponseEntity<CreateGMResponse> createGM(@RequestBody CreateGMRequest data){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createGM(data));
    }

}
