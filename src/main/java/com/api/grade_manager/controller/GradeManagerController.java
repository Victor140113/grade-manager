package com.api.grade_manager.controller;

import com.api.grade_manager.dto.request.CreateGMRequest;
import com.api.grade_manager.dto.response.CreateGMResponse;
import com.api.grade_manager.dto.response.GradeManagerResponse;
import com.api.grade_manager.exception.GMNotFoundException;
import com.api.grade_manager.service.GradeManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeManagerController {

    private final GradeManagerService service;

    public GradeManagerController(GradeManagerService service) {
        this.service = service;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/grade-manager")
    public ResponseEntity<CreateGMResponse> createGM(@RequestBody CreateGMRequest data){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createGM(data));
    }

    @DeleteMapping("/grade-manager/{gmId}")
    public ResponseEntity<?> deleteGM(@PathVariable Long gmId){

        try{
            service.deleteGM(gmId);
            return ResponseEntity.ok().build();
        }catch (GMNotFoundException e){
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/grade-manager")
    public ResponseEntity<List<GradeManagerResponse>> getGMList(){

        return ResponseEntity.ok(service.getGMResponse());
    }

}