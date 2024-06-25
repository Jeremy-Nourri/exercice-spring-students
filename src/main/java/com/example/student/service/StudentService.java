package com.example.student.service;

import com.example.student.data.FakeData;
import com.example.student.exception.StudentNotFoundException;
import com.example.student.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("student")
public class StudentService {

    public static List<Student> studentList = FakeData.studentList;

    public List<Student> getAllStudents() {
        return studentList;
    }

    public Student getStudentByName(String lastName, String firstName) {
        return studentList.stream()
                .filter(student -> student.getLastName().equals(lastName) && student.getFirstName().equals(firstName))
                .findFirst()
                .orElseThrow(()-> new StudentNotFoundException("L'étudiant avec le nom " + lastName + " et le prénom " + firstName + " n'existe pas"));
    }

    public void createStudent(String lastName, String firstName, int age, String email) {
        Student newStudent = Student.builder()
                .lastName(lastName)
                .firstName(firstName)
                .age(age)
                .email(email)
                .build();
        studentList.add(newStudent);
    }

    public List<Student> findStudentsByLastNameOrFirstName(String lastName, String firstName) {
        return studentList.stream()
                .filter(student -> student.getLastName().equals(lastName) || student.getFirstName().equals(firstName))
                .toList();
    }
}
