package com.company;

public class App {
    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setName("Mianta");
        student1.setGpa(4.0);

        Student student2 = new Student();
        student2.setName("Jojo");
        student2.setGpa(4.01);

        student1.greet();
        student2.greet();

        student1.name = "Andrew";


    }
}
