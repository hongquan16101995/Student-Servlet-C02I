package com.example.student_project.model;

public class Classes {
    private Long id;
    private String name;

    public Classes(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Classes(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
