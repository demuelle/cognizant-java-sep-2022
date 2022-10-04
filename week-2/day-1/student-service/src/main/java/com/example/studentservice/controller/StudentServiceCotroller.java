package com.example.studentservice.controller;

import com.example.studentservice.models.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentServiceCotroller {

    private List<Student> studentList = new ArrayList<>(Arrays.asList(
       new Student("Uziel", 2022),
       new Student("JB", 2022),
       new Student("Christe", 2023),
       new Student("Dan", 2000)
    ));

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<Student> getStudents() {
        return studentList;
    }

    @RequestMapping(value = "/students/{idx}", method = RequestMethod.GET)
    public Student lookupStudentByIndex(@PathVariable int idx) {
        return studentList.get(idx);
    }


    @RequestMapping(value = "/students/name/{name}", method = RequestMethod.GET)
    public Student lookupStudentByIndex(@PathVariable String name) {
        Student returnVal = null;
        for (Student student : studentList) {
        return returnVal;
    }
}
