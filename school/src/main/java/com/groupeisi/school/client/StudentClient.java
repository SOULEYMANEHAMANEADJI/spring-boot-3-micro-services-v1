package com.groupeisi.school.client;

import com.groupeisi.school.ResponseApi;
import com.groupeisi.school.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

// @FeignClient(name = "student-service", url = "${application.config.students-url}")
@FeignClient(name = "students")
public interface StudentClient {
    @GetMapping("/api/v1/students/school/{schoolId}")
    ResponseApi<List<Student>> findAllStudentsBySchool(@PathVariable Integer schoolId);
}