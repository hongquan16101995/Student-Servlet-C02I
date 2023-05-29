package com.example.student_project.controller.unit_controller;

import com.example.student_project.service.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HomeServlet", value = "/students1")
public class HomeServlet extends HttpServlet {

    private final StudentService studentService = StudentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("students", studentService.getStudents());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
