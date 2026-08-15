package com.api.grade_manager.controller;

import com.api.grade_manager.dto.request.CreateGURequest;
import com.api.grade_manager.dto.response.CreateGUResponse;
import com.api.grade_manager.exception.GMNotFoundException;
import com.api.grade_manager.service.GradeUpdateService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GradeUpdateController {

    private final GradeUpdateService service;

    public GradeUpdateController(GradeUpdateService service) {
        this.service = service;
    }

    @PostMapping("/grade-manager/semester/course/grade/{gradeId}/grade-update")
    public ResponseEntity<CreateGUResponse> createGradeUpdate(@RequestBody CreateGURequest data, @PathVariable Long gradeId){

        try{
            CreateGUResponse gradeUpdate = service.createGradeUpdate(data, gradeId);
            return ResponseEntity.status(HttpStatus.CREATED).body(gradeUpdate);
        }catch (GMNotFoundException e){
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }

    }
}
