package com.example.student_project;

import java.util.ArrayList;
import java.util.List;

public class StudentManage {
    private final List<Student> students;
    private static StudentManage studentManage;

    private StudentManage() {
        students = new ArrayList<>();
        students.add(new Student(1L,"Dương", 20, "Male", "HN"));
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
}
