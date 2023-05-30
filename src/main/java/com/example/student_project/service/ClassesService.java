package com.example.student_project.service;

import com.example.student_project.DAO.ClassesDAO;
import com.example.student_project.model.Classes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ClassesService {
    private static ClassesService classesService;
    private final ClassesDAO classesDAO;

    private ClassesService() {
        classesDAO = new ClassesDAO();
    }

    public static ClassesService getInstance() {
        if (classesService == null) {
            classesService = new ClassesService();
        }
        return classesService;
    }

    public List<Classes> findAll() {
        return classesDAO.findAll();
    }

    public void addClasses(HttpServletRequest request) {
        String name = request.getParameter("name");
        classesDAO.addClasses(new Classes(name));
    }

    public void updateClasses(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        classesDAO.updateClasses(new Classes(id, name));
    }

    public Classes getById(Long id) {
        return classesDAO.findById(id);
    }

    public void deleteById(Long id) {
        classesDAO.deleteById(id);
    }
}
