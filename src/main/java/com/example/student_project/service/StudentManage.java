package com.example.student_project.service;

import com.example.student_project.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentManage {
    private final List<Student> students;
    private static StudentManage studentManage;

    private StudentManage() {
        students = new ArrayList<>();
        students.add(new Student(1L,"Duong", 20, "Male", "HN"));
        students.add(new Student(2L,"Dung", 21, "Male", "HN"));
        students.add(new Student(3L,"Thai", 22, "Male", "HN"));
    }

    public static StudentManage getInstance() {
        if (studentManage == null) {
            studentManage = new StudentManage();
        }
        return studentManage;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student getById(Long id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void deleteById(Long id) {
        Student student = getById(id);
        if (student != null) {
            students.remove(student);
        }
    }

    public List<Student> searchByName(String name) {
        List<Student> studentsSearch = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().contains(name)) {
                studentsSearch.add(student);
            }
        }
        return studentsSearch;
    }
}
