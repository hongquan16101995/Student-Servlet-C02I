package com.example.student_project.service;

import com.example.student_project.DAO.StudentDAO;
import com.example.student_project.model.Classes;
import com.example.student_project.model.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class StudentService {

    private final StudentDAO studentDAO;
    private final ClassesService classesService;
    private static StudentService studentService;

    private StudentService() {
        studentDAO = new StudentDAO();
        classesService = ClassesService.getInstance();
    }

    public static StudentService getInstance() {
        if (studentService == null) {
            studentService = new StudentService();
        }
        return studentService;
    }

    public List<Student> getStudents() {
        return studentDAO.findAll();
    }

    public void save(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        Long classId = Long.parseLong(request.getParameter("classes"));
        Classes classes = classesService.getById(classId);
        if (id != null) {
            Long idUpdate = Long.parseLong(id);
            studentDAO.updateStudent(new Student(idUpdate, name, age, gender, address, classes));
        } else {
            studentDAO.addStudent(new Student(name, age, gender, address, classes));
        }
    }

    public Student getById(Long id) {
        return studentDAO.findById(id);
    }

    public void deleteById(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        studentDAO.deleteById(id);
    }

    public List<Student> searchByName(HttpServletRequest request) {
        String search = request.getParameter("search");
        return studentDAO.searchByName(search);
    }

    public boolean checkById(Long id) {
        Student student = studentDAO.findById(id);
        return student != null;
    }
}
