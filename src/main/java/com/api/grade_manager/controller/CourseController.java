package com.api.grade_manager.controller;

import com.api.grade_manager.dto.request.CreateCourseRequest;
import com.api.grade_manager.dto.response.CreateCourseResponse;
import com.api.grade_manager.exception.CourseNotFoundException;
import com.api.grade_manager.exception.SemesterNotFoundException;
import com.api.grade_manager.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping("/grade-manager/semester/{idSemester}/course")
    public ResponseEntity<CreateCourseResponse> createCourse(@RequestBody CreateCourseRequest data, @PathVariable Long idSemester){

        try{
            CreateCourseResponse course = service.createCourse(data, idSemester);
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
}
