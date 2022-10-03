package com.company;

public class MyPersonsApplication {
    public static void main(String[] args) {

        Person person1 = new Person();
        person1.setName("Richard");

        Person anotherPerson = new Person();
        anotherPerson.setName("Alla");

//        Person aThirdPerson;
//        aThirdPerson.setName("Doug");

        anotherPerson.sayHello();
        person1.sayHello();
        person1.sayHello();
    }
}
