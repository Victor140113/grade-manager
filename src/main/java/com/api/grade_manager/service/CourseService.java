package com.api.grade_manager.service;

import com.api.grade_manager.dto.request.CreateCourseRequest;
import com.api.grade_manager.dto.response.CreateCourseResponse;
import com.api.grade_manager.entity.CourseEntity;
import com.api.grade_manager.entity.GradeEntity;
import com.api.grade_manager.entity.SemesterEntity;
import com.api.grade_manager.exception.SemesterNotFoundException;
import com.api.grade_manager.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository database;
    private final SemesterService semesterService;
    private final GradeService gradeService;

    public CourseService(CourseRepository database, SemesterService semesterService, GradeService gradeService) {
        this.database = database;
        this.semesterService = semesterService;
        this.gradeService = gradeService;
    }

    // Métodos Externos

    public CreateCourseResponse createCourse(CreateCourseRequest data, Long idSemester){

        SemesterEntity semester = semesterService.getSemesterById(idSemester);
        if(semester == null) throw new SemesterNotFoundException(" Semestre não encontrado!");

        CourseEntity course = new CourseEntity();
        course.setNome(data.getNome());
        course.getGrades().add(new GradeEntity(null, 1));
        course.getGrades().add(new GradeEntity(null, 2));
        course.setSemester(semester);
        semester.getCourse().add(course);

        database.save(course);

        gradeService.saveGradeOnCourse(course.getGrades().getFirst(), course);
        gradeService.saveGradeOnCourse(course.getGrades().getLast(), course);

        return new CreateCourseResponse(course.getNome());
    }

    // Métodos Internos

    public CourseEntity getCourseById(Long id){
        return database.findById(id).orElse(null);
    }

}
