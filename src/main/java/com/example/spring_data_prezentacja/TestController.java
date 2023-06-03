package com.example.spring_data_prezentacja;

import com.example.spring_data_prezentacja.entity.*;
import com.example.spring_data_prezentacja.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class TestController {

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    private final CourseRepository courseRepository;


    // GETs with JPARepository methods
    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        return studentRepository.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/teacher/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id){
        return teacherRepository.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id){
        return courseRepository.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/student")
    public ResponseEntity<?> getAllStudents(){
        return ResponseEntity.ok(studentRepository.findAll());
    }
    @GetMapping("/teacher")
    public ResponseEntity<?> getAllTeachers(){ return ResponseEntity.ok(teacherRepository.findAll()); }
    @GetMapping("/course")
    public ResponseEntity<?> getAllCourses(){
        return ResponseEntity.ok(courseRepository.findAll());
    }


    // GETs with built-in queries
    @GetMapping("/course/nullteacher")
    public ResponseEntity<?> findCourseWithNullTeacher() {
        return ResponseEntity.ok(courseRepository.findByTeacherIsNull());
    }


    @GetMapping("/student/namelike/{str}")
    public ResponseEntity<?> findStudentNameContaining(@PathVariable String str) {
        return ResponseEntity.ok(studentRepository.findByNameContainsIgnoreCase(str));
    }


    //GETs with custom queries
    @GetMapping("/student/custom/jpql/{student_id}")
    public ResponseEntity<?> getCustomQueryJPQL(@PathVariable Long student_id) {
        return ResponseEntity.ok(studentRepository.customQueryJPQL(student_id));
    }

    @GetMapping("/student/custom/native/{student_id}")
    public ResponseEntity<?> getCustomQueryNative(@PathVariable Long student_id) {
        return ResponseEntity.ok(studentRepository.customQueryNative(student_id));
    }

    @GetMapping("/teacher/{teacher_name}/page/{page_id}")
    public ResponseEntity<?> getTeachersByPageNumber(@PathVariable String teacher_name, @PathVariable Integer page_id){
        return ResponseEntity.ok(teacherRepository.findByNameContainsIgnoreCase(teacher_name, PageRequest.of(page_id, 2)));
    }

    //POSTs

    @PostMapping("/student")
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @PostMapping("/teacher")
    public ResponseEntity<?> saveTeacher(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherRepository.save(teacher));
    }

    @PostMapping("/course")
    public ResponseEntity<?> saveCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseRepository.save(course));
    }

    //DELETEs

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id){
        studentRepository.deleteById(id);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<?> deleteTeacherById(@PathVariable Long id){
        teacherRepository.deleteById(id);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Long id){
        courseRepository.deleteById(id);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/student")
    public ResponseEntity<?> deleteAllStudents(){
        studentRepository.deleteAll();
        return ResponseEntity.ok("");
    }
    @DeleteMapping("/teacher")
    public ResponseEntity<?> deleteAllTeachers(){
        teacherRepository.deleteAll();
        return ResponseEntity.ok("");
    }
    @DeleteMapping("/course")
    public ResponseEntity<?> deleteAllCourses(){
        courseRepository.deleteAll();
        return ResponseEntity.ok("");
    }

    //PUTs
    @PutMapping("/course/{course_id}/student/{student_id}")
    public ResponseEntity<?> addStudentToCourse(@PathVariable Long course_id, @PathVariable Long student_id){
        Course course = courseRepository.findById(course_id).get();
        Student student = studentRepository.findById(student_id).get();
        course.addStudentToCourse(student);
        return ResponseEntity.ok(courseRepository.save(course));
    }

    @PutMapping("/course/{course_id}/teacher/{teacher_id}")
    public ResponseEntity<?> addTeacherToCourse(@PathVariable Long course_id, @PathVariable Long teacher_id){
        Course course = courseRepository.findById(course_id).get();
        Teacher teacher = teacherRepository.findById(teacher_id).get();
        course.addTeacherToCourse(teacher);
        return ResponseEntity.ok(courseRepository.save(course));
    }
}
