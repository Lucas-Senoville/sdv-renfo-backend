package com.renfo_backend.Renfo_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renfo_backend.Renfo_backend.dto.course.CourseDto;
import com.renfo_backend.Renfo_backend.dto.course.CourseDtoMapper;
import com.renfo_backend.Renfo_backend.dto.grade.GradeDto;
import com.renfo_backend.Renfo_backend.dto.grade.GradeDtoMapper;
import com.renfo_backend.Renfo_backend.dto.grade.GradeWithStudentsDto;
import com.renfo_backend.Renfo_backend.dto.lesson.LessonDto;
import com.renfo_backend.Renfo_backend.dto.lesson.LessonDtoMapper;
import com.renfo_backend.Renfo_backend.dto.student.StudentDto;
import com.renfo_backend.Renfo_backend.dto.student.StudentDtoMapper;
import com.renfo_backend.Renfo_backend.dto.subject.SubjectDto;
import com.renfo_backend.Renfo_backend.dto.subject.SubjectDtoMapper;
import com.renfo_backend.Renfo_backend.dto.teacher.TeacherDto;
import com.renfo_backend.Renfo_backend.dto.teacher.TeacherDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Grade;
import com.renfo_backend.Renfo_backend.exceptions.GradeNotFoundException;
import com.renfo_backend.Renfo_backend.repository.CourseRepository;
import com.renfo_backend.Renfo_backend.repository.GradeRepository;
import com.renfo_backend.Renfo_backend.repository.LessonRepository;
import com.renfo_backend.Renfo_backend.repository.StudentRepository;
import com.renfo_backend.Renfo_backend.repository.SubjectRepository;
import com.renfo_backend.Renfo_backend.repository.TeacherRepository;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
    private final GradeRepository repository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    public GradeController(GradeRepository repository, CourseRepository courseRepository,
            StudentRepository studentRepository, LessonRepository lessonRepository,
            SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping
    public List<GradeDto> getGrades() {
        return repository.findAll().stream().map(GradeDtoMapper::toDto).toList();
    }

    @PostMapping
    public GradeDto newGrade(@RequestBody GradeDto gradeDto) {
        Grade grade = GradeDtoMapper.toGrade(gradeDto);

        Grade newGrade = repository.save(grade);

        return GradeDtoMapper.toDto(newGrade);
    }

    @GetMapping("/{id}")
    public GradeWithStudentsDto getGrade(@PathVariable Long id) {
        return repository.findById(id).map(GradeDtoMapper::toDtoWithStudents)
                .orElseThrow(() -> new GradeNotFoundException(id));
    }

    @GetMapping("/{id}/courses")
    public List<CourseDto> getCourses(@PathVariable Long id) {
        return courseRepository.findByGradeId(id).stream().map(CourseDtoMapper::toDto).toList();
    }

    @GetMapping("/{id}/students")
    public List<StudentDto> getStudents(@PathVariable Long id) {
        return studentRepository.findByGradeId(id).stream().map(StudentDtoMapper::toDto).toList();
    }

    @GetMapping("/{id}/lessons")
    public List<LessonDto> getLessons(@PathVariable Long id) {
        return lessonRepository.findByCourseGradeId(id).stream().map(LessonDtoMapper::toDto).toList();
    }

    @GetMapping("/{id}/subjects")
    public List<SubjectDto> getSubjects(@PathVariable Long id) {
        return subjectRepository.findByCoursesGradeId(id).stream().map(SubjectDtoMapper::toDto).toList();
    }

    @GetMapping("/{id}/teachers")
    public List<TeacherDto> getTeachers(@PathVariable Long id) {
        return teacherRepository.findByCoursesGradeId(id).stream().map(TeacherDtoMapper::toDto).toList();
    }

    @PutMapping("/{id}")
    GradeDto replaceGrade(@RequestBody GradeDto newGrade, @PathVariable Long id) {
        return GradeDtoMapper.toDto(repository.findById(id)
                .map(grade -> {
                    grade.setName(newGrade.getName());

                    return repository.save(grade);
                })
                .orElseGet(() -> {
                    return repository.save(GradeDtoMapper.toGrade(newGrade));
                }));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteGrade(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
