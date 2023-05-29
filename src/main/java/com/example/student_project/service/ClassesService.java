package com.example.student_project.service;

import com.example.student_project.model.Classes;

import java.util.ArrayList;
import java.util.List;

public class ClassesService {
    private final List<Classes> classesList;
    private static ClassesService classesService;

    private ClassesService() {
        classesList = new ArrayList<>();
        classesList.add(new Classes(1L,"C0223I1"));
        classesList.add(new Classes(2L,"C0223H1"));
        classesList.add(new Classes(3L,"C0323G1"));
    }

    public static ClassesService getInstance() {
        if (classesService == null) {
            classesService = new ClassesService();
        }
        return classesService;
    }

    public List<Classes> getClasses() {
        return classesList;
    }

    public void addClasses(Classes classes) {
        classesList.add(classes);
    }

    public Classes getById(Long id) {
        for (Classes classes : classesList) {
            if (classes.getId().equals(id)) {
                return classes;
            }
        }
        return null;
    }

    public void deleteById(Long id) {
        Classes classes = getById(id);
        if (classes != null) {
            classesList.remove(classes);
        }
    }
}
