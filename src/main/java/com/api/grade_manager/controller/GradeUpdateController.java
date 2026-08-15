package com.api.grade_manager.controller;

import com.api.grade_manager.dto.request.CreateGURequest;
import com.api.grade_manager.dto.request.PutGURequest;
import com.api.grade_manager.dto.response.CreateGUResponse;
import com.api.grade_manager.dto.response.GradeUpdateResponse;
import com.api.grade_manager.dto.response.PutGUResponse;
import com.api.grade_manager.exception.ConflictException;
import com.api.grade_manager.exception.GMNotFoundException;
import com.api.grade_manager.exception.GUNotFoundException;
import com.api.grade_manager.exception.GradeNotFoundException;
import com.api.grade_manager.service.GradeUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @DeleteMapping("/grade-manager/semester/course/grade/{gradeId}/grade-update/{guId}")
    public ResponseEntity<?> deleteGradeUpdate(@PathVariable Long gradeId, @PathVariable Long guId){

        try{
            service.deleteGradeUpdate(gradeId, guId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (GMNotFoundException | GUNotFoundException e){

            System.out.println(e);
            return ResponseEntity.notFound().build();

        }catch (ConflictException e){

            System.out.println(e);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/grade-manager/semester/course/grade/{gradeId}/grade-update")
    public ResponseEntity<List<GradeUpdateResponse>> getGUList(@PathVariable Long gradeId){

        return ResponseEntity.ok(service.getGUList(gradeId));
    }

    @PutMapping("/grade-manager/semester/course/grade/{gradeId}/grade-update/{guId}")
    public ResponseEntity<PutGUResponse> putGradeUpdate(@RequestBody PutGURequest data, @PathVariable Long guId, @PathVariable Long gradeId){

        try {
            PutGUResponse gu = service.putGradeUpdate(data, guId, gradeId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(gu);
        }catch (GUNotFoundException | GradeNotFoundException e){
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }
}
