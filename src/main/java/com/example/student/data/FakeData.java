package com.example.student.data;


import com.example.student.model.Student;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class FakeData {
    public static List<Student> studentList = new ArrayList<>();

    private FakeData() {}

    static {
        studentList.add(new Student("Doe", "John", 20, "john@example.com"));
        studentList.add(new Student("Smith", "Will", 22, "smith@example.com"));
    }

}
