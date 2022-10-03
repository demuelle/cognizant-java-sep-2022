package com.company;

public class Person {
    private String name;

    public void setName(String newName) {
        this.name = newName;
    }

    public void sayHello() {
        System.out.println("Hello, I am " + name);
    }
}
