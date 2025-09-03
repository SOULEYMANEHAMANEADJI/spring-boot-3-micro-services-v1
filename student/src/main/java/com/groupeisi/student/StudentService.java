package com.groupeisi.student;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudentsBySchool(Integer schoolId) {
        return studentRepository.findAllBySchoolId(schoolId);
    }

    public Student updateStudent(Integer id, Student details) {
        return studentRepository.findById(id).map(student -> {
            student.setFirstName(details.getFirstName());
            student.setLastName(details.getLastName());
            student.setEmail(details.getEmail());
            student.setSchoolId(details.getSchoolId());
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
