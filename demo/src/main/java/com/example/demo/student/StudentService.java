package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (student.getEmail() == null || student.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        }
        if (student.getDob() == null) {
            throw new IllegalArgumentException("Date of Birth is required.");
        }

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email is already taken.");
        }
        studentRepository.save(student);
    }


    public void deleteStudent(Long studentID) {
        boolean exist = studentRepository.existsById(studentID);
        if (!exist) {
            throw new IllegalStateException("Student ID " + studentID + " does not exist.");
        }
        studentRepository.deleteById(studentID);
        System.out.println("Student ID " + studentID + " was deleted successfully.");
    }


    @Transactional
    public void updateStudent(Long studentID, String name, String email) {
        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new IllegalStateException("Student ID " + studentID + " does not exist."));

        if (name != null && !name.trim().isEmpty() && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && !email.trim().isEmpty() && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email is already taken.");
            }
            student.setEmail(email);
        }
    }
}
