package com.example.studentservice.models;

import java.util.Objects;

public class Student {
    private String name;
    private int graduationYear;

    public Student(String name, int graduationYear) {
        this.name = name;
        this.graduationYear = graduationYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return graduationYear == student.graduationYear && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, graduationYear);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", graduationYear=" + graduationYear +
                '}';
    }
}
