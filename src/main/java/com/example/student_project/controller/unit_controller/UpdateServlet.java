package com.example.student_project.controller.unit_controller;

import com.example.student_project.model.Student;
import com.example.student_project.service.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UpdateServlet", value = "/students/update")
public class UpdateServlet extends HttpServlet {

    private final StudentService studentService = StudentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Student student = studentService.getById(id);
        if (student != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("update.jsp");
            request.setAttribute("student", student);
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/404.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");

        Student student = studentService.getById(id);
        if (student != null) {
            student.setName(name);
            student.setAge(age);
            student.setGender(gender);
            student.setAddress(address);
            response.sendRedirect("/students");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }
}
