package com.example.dao;

import com.example.annotation.Loggable;
import com.example.model.Student;
import jakarta.validation.Valid;

import java.util.List;

public interface StudentDAO {
    void create(Student student);

    @Loggable
    void delete(int id);

    @Loggable
    Student read(int id);

    void update(Student student);

    List<Student> getAll();

    Student getById(int id);

    void save(@Valid Student student);
}
