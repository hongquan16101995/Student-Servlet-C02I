package com.example.student_project.controller.unit_controller;

import com.example.student_project.model.Student;
import com.example.student_project.service.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CreateServlet", value = "/students/create")
public class CreateServlet extends HttpServlet {

    private final StudentService studentService = StudentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("create.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");

        Student student = new Student(id, name, age, gender, address);
        studentService.addStudent(student);

        response.sendRedirect("/students");
    }
}
