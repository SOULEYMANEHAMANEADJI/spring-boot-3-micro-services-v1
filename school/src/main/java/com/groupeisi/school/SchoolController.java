package com.groupeisi.school;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ResponseApi<School>> createSchool(@Valid @RequestBody School school) {
        School saved = schoolService.saveSchool(school);
        return ResponseEntity.status(201).body(
                ResponseApi.<School>builder()
                        .status(201)
                        .message("School created successfully")
                        .data(saved)
                        .build()
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<ResponseApi<List<School>>> getAllSchools() {
        List<School> list = schoolService.getAllSchools();
        return ResponseEntity.ok(
                ResponseApi.<List<School>>builder()
                        .status(200)
                        .message("List of all schools")
                        .data(list)
                        .build()
        );
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<School>> getSchool(@PathVariable Integer id) {
        return schoolService.getSchoolById(id)
                .map(school -> ResponseEntity.ok(
                        ResponseApi.<School>builder()
                                .status(200)
                                .message("School found")
                                .data(school)
                                .build()))
                .orElse(ResponseEntity.status(404).body(
                        ResponseApi.<School>builder()
                                .status(404)
                                .message("School not found")
                                .data(null)
                                .build()
                ));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<School>> updateSchool(@PathVariable Integer id, @Valid @RequestBody School schoolDetails) {
        try {
            School updated = schoolService.updateSchool(id, schoolDetails);
            return ResponseEntity.ok(
                    ResponseApi.<School>builder()
                            .status(200)
                            .message("School updated")
                            .data(updated)
                            .build()
            );
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(
                    ResponseApi.<School>builder()
                            .status(404)
                            .message("School not found")
                            .data(null)
                            .build()
            );
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<Void>> deleteSchool(@PathVariable Integer id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.ok(
                ResponseApi.<Void>builder()
                        .status(200)
                        .message("School deleted")
                        .data(null)
                        .build()
        );
    }

    // READ school + students
    @GetMapping("/{id}/details")
    public ResponseEntity<ResponseApi<FullSchoolResponse>> getSchoolWithStudents(@PathVariable Integer id) {
        FullSchoolResponse details = schoolService.findSchoolWithStudents(id);
        return ResponseEntity.ok(
                ResponseApi.<FullSchoolResponse>builder()
                        .status(200)
                        .message("School and students info")
                        .data(details)
                        .build()
        );
    }
}
