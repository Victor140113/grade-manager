package com.api.grade_manager.controller;

import com.api.grade_manager.dto.request.CreateCourseRequest;
import com.api.grade_manager.dto.response.CourseResponse;
import com.api.grade_manager.dto.response.CreateCourseResponse;
import com.api.grade_manager.exception.CourseNotFoundException;
import com.api.grade_manager.exception.SemesterNotFoundException;
import com.api.grade_manager.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping("/grade-manager/semester/{semesterId}/course")
    public ResponseEntity<CreateCourseResponse> createCourse(@RequestBody CreateCourseRequest data, @PathVariable Long semesterId){

        try{
            CreateCourseResponse course = service.createCourse(data, semesterId);
            return ResponseEntity.status(HttpStatus.CREATED).body(course);
        }catch (SemesterNotFoundException e){
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/grade-manager/semester/{semesterId}/course/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId, @PathVariable Long semesterId){

        try{
            service.deleteCourse(courseId, semesterId);
            return ResponseEntity.ok().build();
        }catch (CourseNotFoundException | SemesterNotFoundException e){
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/grade-manager/semester/{semesterId}/course")
    public ResponseEntity<List<CourseResponse>> getCourseList(@PathVariable Long semesterId){

        return ResponseEntity.ok(service.getCourseList(semesterId));
    }
}
