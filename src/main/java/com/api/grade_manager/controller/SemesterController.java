package com.api.grade_manager.controller;

import com.api.grade_manager.dto.request.CreateSemesterRequest;
import com.api.grade_manager.dto.response.CreateSemesterResponse;
import com.api.grade_manager.exception.GMNotFoundException;
import com.api.grade_manager.exception.SemesterNotFoundException;
import com.api.grade_manager.service.SemesterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SemesterController {

    private final SemesterService service;

    public SemesterController(SemesterService service) {
        this.service = service;
    }

    @PostMapping("/grade-manager/{idGM}/semester")
    public ResponseEntity<CreateSemesterResponse> createSemester(@RequestBody CreateSemesterRequest data, @PathVariable Long idGM){

        try {
            CreateSemesterResponse semesterResponse = service.createSemester(data, idGM);
            return ResponseEntity.status(HttpStatus.CREATED).body(semesterResponse);
        }catch (GMNotFoundException e){
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/grade-manager/{gmId}/semester/{semesterId}")
    public ResponseEntity<?> deleteSemester(@PathVariable Long gmId, @PathVariable Long semesterId){

        try{

            service.deleteSemester(gmId, semesterId);
            return ResponseEntity.ok().build();
        }catch (GMNotFoundException | SemesterNotFoundException e){
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

}
