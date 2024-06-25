package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("student", new Student());
        return "registration";
    }

    @GetMapping("/list")
    public String displayListOfStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "list";
    }

    @GetMapping("/student/{lastname}/{firstname}")
    public String displayStudentByName(@PathVariable(name= "lastname") String lastname, @PathVariable(name="firstname")String firstname, Model model) {
        Student studentFound = studentService.getStudentByName(lastname, firstname);
        model.addAttribute("student", studentFound);
        return "student";
}

    @PostMapping("/create-student")
    public String createStudent(@ModelAttribute("student") Student student) {
        studentService.createStudent(student.getLastName(), student.getFirstName(), student.getAge(), student.getEmail());
        return "redirect:/list";
    }

    @GetMapping("/search")
    public String searchStudent(@RequestParam(name = "lastname") String lastname, @RequestParam(name = "firstname") String firstname, Model model) {
        List<Student> studentsFound = studentService.findStudentsByLastNameOrFirstName(lastname, firstname);
        model.addAttribute("students", studentsFound);
        return "results";
    }

}
