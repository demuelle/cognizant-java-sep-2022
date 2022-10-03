package com.company;

public class Student {
    private String name;
    private double gpa;

    public void setName(String name) {
        this.name = name;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void greet() {
        System.out.println("Hello, I am " + name + " and I have a " + gpa + " GPA.");
    }

//    public int attendClasses() {
//        int room = lookUpRoomForClass();
//    }
//
//    private int lookUpRoomForClass() {
//
//        return 237;
//    }
}
