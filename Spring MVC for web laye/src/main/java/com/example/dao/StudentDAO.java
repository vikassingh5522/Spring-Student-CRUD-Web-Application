package com.example.dao;

import com.example.annotation.Loggable;
import com.example.model.Student;

import java.util.List;

public interface StudentDAO {
    void create(Student student);

    @Loggable
    void delete(int id);

    @Loggable
    Student read(int id);

    void update(Student student);

    List<Student> getAll();
}
