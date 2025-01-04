package com.example.demo.student;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentID;

    private String email;
    private String name;
    private LocalDate dob;

    @Transient
    private Integer age;

    public Student() {

    }

    public Student(Long studentID, String name, String email,  LocalDate dob) {
        this.studentID = studentID;
        this.email = email;
        this.dob = dob;
        this.name = name;
    }

    public Student( String name, String email,  LocalDate dob) {
        this.dob = dob;
        this.email = email;
        this.name = name;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();

    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
