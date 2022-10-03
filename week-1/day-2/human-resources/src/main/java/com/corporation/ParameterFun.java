package com.corporation;

public class ParameterFun {

    public static void main(String[] args) {
        Person myPerson = new Person("Nick", "CEO");
        changeMyName(myPerson);
        System.out.println("Now, myPerson's name is " + myPerson.getName());

        int myNum = 5;
        System.out.println("Before calling addOneToMe, myNum is " + myNum);
        addOneToMe(myNum);
        System.out.println("After calling addOneToMe, myNum is " + myNum);

        InsurancePolicy ip = new InsurancePolicy("E11211", "Cigna", 2022);

        System.out.println("The insurance policy: " + ip.toString());
        System.out.println("The insurance policy b: " + ip);
    }


    public static void addOneToMe(int number) {
        System.out.println("Number passed in is " + number);
        number = number + 1;
        System.out.println("Number value after adding one = " + number);
    }

    public static void changeMyName(Person person) {
        System.out.println("Name of the person passed to this method is " + person.getName() + ".");
        person.setName("YOUR NAME HAS BEEN CHANGED");
        System.out.println("Name of the person after changing it is " + person.getName() + ".");
    }
}
