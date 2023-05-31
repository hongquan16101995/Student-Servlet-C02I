package com.example.student_project.service;

import com.example.student_project.DAO.ClassesDAO;
import com.example.student_project.model.Classes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ClassesService {

    private final ClassesDAO classesDAO;
    private static ClassesService classesService;

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

    public Classes getById(Long id) {
        return classesDAO.findById(id);
    }

    public void save(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        if (id != null) {
            Long idUpdate = Long.parseLong(id);
            classesDAO.updateClasses(new Classes(idUpdate, name));
        } else {
            classesDAO.addClasses(new Classes(name));
        }
    }

    public void deleteById(Long id) {
        classesDAO.deleteById(id);
    }

    public boolean checkById(Long id) {
        Classes classes = classesService.getById(id);
        return classes != null;
    }
}
