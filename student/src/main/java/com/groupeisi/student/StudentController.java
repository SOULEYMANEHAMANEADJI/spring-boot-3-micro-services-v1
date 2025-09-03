package com.groupeisi.student;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ResponseApi<Student>> createStudent(@Valid @RequestBody Student student) {
        Student saved = studentService.saveStudent(student);
        return ResponseEntity.status(201).body(
                ResponseApi.<Student>builder()
                        .status(201)
                        .message("Student created successfully")
                        .data(saved)
                        .build()
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<ResponseApi<List<Student>>> getAllStudents() {
        List<Student> list = studentService.getAllStudents();
        return ResponseEntity.ok(
                ResponseApi.<List<Student>>builder()
                        .status(200)
                        .message("List of all students")
                        .data(list)
                        .build()
        );
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<Student>> getStudent(@PathVariable Integer id) {
        return studentService.getStudentById(id)
                .map(student -> ResponseEntity.ok(
                        ResponseApi.<Student>builder()
                                .status(200)
                                .message("Student found")
                                .data(student)
                                .build()))
                .orElse(ResponseEntity.status(404).body(
                        ResponseApi.<Student>builder()
                                .status(404)
                                .message("Student not found")
                                .data(null)
                                .build()
                ));
    }

    // READ BY SCHOOL
    @GetMapping("/school/{schoolId}")
    public ResponseEntity<ResponseApi<List<Student>>> getStudentsBySchool(@PathVariable Integer schoolId) {
        List<Student> list = studentService.getAllStudentsBySchool(schoolId);
        return ResponseEntity.ok(
                ResponseApi.<List<Student>>builder()
                        .status(200)
                        .message("List of students for the school")
                        .data(list)
                        .build()
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<Student>> updateStudent(@PathVariable Integer id, @Valid @RequestBody Student studentDetails) {
        try {
            Student updated = studentService.updateStudent(id, studentDetails);
            return ResponseEntity.ok(
                    ResponseApi.<Student>builder()
                            .status(200)
                            .message("Student updated")
                            .data(updated)
                            .build()
            );
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(
                    ResponseApi.<Student>builder()
                            .status(404)
                            .message("Student not found")
                            .data(null)
                            .build()
            );
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<Void>> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(
                ResponseApi.<Void>builder()
                        .status(200)
                        .message("Student deleted")
                        .data(null)
                        .build()
        );
    }
}
