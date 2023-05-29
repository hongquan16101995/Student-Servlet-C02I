package com.example.student_project.controller;

import com.example.student_project.model.Classes;
import com.example.student_project.service.ClassesService;
import com.example.student_project.service.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ClassesServlet", value = "/classes")
public class ClassesServlet extends HttpServlet {

    private final ClassesService classesService = ClassesService.getInstance();
    private final StudentService studentService = StudentService.getInstance();

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
        }
    }

    private void findAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/classes/home.jsp");
        request.setAttribute("classes", classesService.getClasses());
        requestDispatcher.forward(request, response);
    }

    private void createGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/classes/create.jsp");
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");

        Classes classes = new Classes(id, name);
        classesService.addClasses(classes);
        response.sendRedirect("/classes");
    }

    private void updateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Classes classes = classesService.getById(id);

        if (classes != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/classes/update.jsp");
            request.setAttribute("classes", classes);
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/404.jsp");
        }
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");

        Classes classes = classesService.getById(id);

        if (classes != null) {
           classes.setName(name);
            response.sendRedirect("/classes");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Classes classes = classesService.getById(id);
        if (classes != null) {
            classesService.deleteById(id);
            studentService.deleteByClasses(classes);
            response.sendRedirect("/classes");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }
}
