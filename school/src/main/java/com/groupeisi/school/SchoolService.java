package com.groupeisi.school;

import com.groupeisi.school.client.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final StudentClient studentClient;

    // CREATE
    public School saveSchool(School school) {
        return schoolRepository.save(school);
    }

    // READ ALL
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    // READ BY ID
    public Optional<School> getSchoolById(Integer schoolId) {
        return schoolRepository.findById(schoolId);
    }

    // UPDATE
    public School updateSchool(Integer id, School schoolDetails) {
        return schoolRepository.findById(id).map(school -> {
            school.setName(schoolDetails.getName());
            school.setEmail(schoolDetails.getEmail());
            return schoolRepository.save(school);
        }).orElseThrow(() -> new RuntimeException("School not found"));
    }

    // DELETE
    public void deleteSchool(Integer id) {
        schoolRepository.deleteById(id);
    }

    // GET SCHOOL + STUDENTS
    public FullSchoolResponse findSchoolWithStudents(Integer schoolId) {
        var school = schoolRepository.findById(schoolId)
                .orElse(School.builder()
                        .name("NOT_FOUND")
                        .email("NOT_FOUND")
                        .build()
                );
        var apiResponse = studentClient.findAllStudentsBySchool(schoolId);
        List<Student> students = (apiResponse != null && apiResponse.getData() != null) ? apiResponse.getData() : List.of();
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }
}
