package com.example.controller;

import com.example.annotation.Loggable;
import com.example.dao.StudentDAO;
import com.example.model.Student;
import com.example.util.LogViewer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentDAO studentDAO;
    private final LogViewer logViewer;

    @Autowired
    public StudentController(StudentDAO studentDAO, LogViewer logViewer) {
        this.studentDAO = studentDAO;
        this.logViewer = logViewer;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("students", List.of()); // empty list
        return "index";
    }

    @Loggable
    @GetMapping("/students")
    public String listStudents(Model model) {
        List<Student> students = studentDAO.getAll();
        model.addAttribute("students", students);
        return "list";
    }

    @Loggable
    @GetMapping("/students/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "create";
    }

    @Loggable
    @PostMapping("/students/create")
    public String createStudent(@ModelAttribute("student") Student student) {
        studentDAO.create(student);
        return "redirect:/students";
    }

    @Loggable
    @GetMapping("/students/{id}")
    public String viewStudent(@PathVariable("id") int id, Model model) {
        Student student = studentDAO.read(id);
        if (student == null) {
            model.addAttribute("error", "Student not found");
            return "view";
        }
        model.addAttribute("student", student);
        return "view";
    }

    @Loggable
    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Student student = studentDAO.read(id);
        if (student == null) {
            model.addAttribute("error", "Student not found");
            return "edit";
        }
        model.addAttribute("student", student);
        return "edit";
    }

    @Loggable
    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable("id") int id, @ModelAttribute("student") Student student) {
        student.setId(id);
        studentDAO.update(student);
        return "redirect:/students";
    }

    @Loggable
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        studentDAO.delete(id);
        return "redirect:/students";
    }

    @Loggable
    @GetMapping("/students/logs")
    public String viewLogs(Model model) {
        try {
            List<String> logLines = Collections.singletonList(logViewer.getLastLogLines(1000));
            String joinedLogs = String.join("\n", logLines);
            model.addAttribute("logs", joinedLogs);
        } catch (Exception e) {
            logger.error("Error reading logs", e);
            model.addAttribute("error", "Error reading logs: " + e.getMessage());
        }
        return "logs";
    }
}
