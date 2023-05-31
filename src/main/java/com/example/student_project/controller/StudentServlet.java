package com.example.student_project.controller;

import com.example.student_project.service.ClassesService;
import com.example.student_project.service.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {

    private final StudentService studentService = StudentService.getInstance();
    private final ClassesService classesService = ClassesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createGet(request, response);
                break;
            case "update":
                updateGet(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            default:
                findAdd(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createPost(request, response);
                break;
            case "update":
                updatePost(request, response);
                break;
            case "search":
                search(request, response);
                break;
        }
    }

    private void findAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("students", studentService.getStudents());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/student/home.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("classes", classesService.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/student/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long classId = Long.parseLong(request.getParameter("classes"));
        if (classesService.checkById(classId)) {
            studentService.save(request);
            response.sendRedirect("/students");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }

    private void updateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        if (studentService.checkById(id)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/student/update.jsp");
            request.setAttribute("student", studentService.getById(id));
            request.setAttribute("classes", classesService.findAll());
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/404.jsp");
        }
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long classId = Long.parseLong(request.getParameter("classes"));
        Long id = Long.parseLong(request.getParameter("id"));

        if (studentService.checkById(id) && classesService.checkById(classId)) {
            studentService.save(request);
            response.sendRedirect("/students");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        studentService.deleteById(request);
        response.sendRedirect("/students");
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {;
        request.setAttribute("students", studentService.searchByName(request));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/student/home.jsp");
        requestDispatcher.forward(request, response);
    }
}
