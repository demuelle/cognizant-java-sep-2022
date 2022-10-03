package com.wild;

public class Savannah {
    public static void main(String[] args) {
        System.out.println("So far, there are " + Lion.howManyLionsAreThere());
        Lion lion1 = new Lion("Nala", 0, 260);
//        lion1.setName("Nala");
//        lion1.setAge(4);
//        lion1.setWeight(270);

        System.out.println("AHH! A Lion! Named " + lion1.getName());
        System.out.println("AHH! The lion is " + lion1.getAge() + " years old.");
        System.out.println("OH NO! The lion weighs " + lion1.getWeight() + " pounds.");

        System.out.println("So far, there are " + Lion.howManyLionsAreThere());

        Lion lion2 = new Lion("Scar", 81, 403);
        System.out.println("Ahh! Another lion!!!: " + lion2.generateDescription());

        System.out.println("==before==");
        lion1.roar();
        System.out.println("==after==");
        lion2.roar();

        lion1.pounce();

        lion2.eat(26);
        System.out.println(lion2.generateDescription());

        System.out.println("So far, there are " + Lion.howManyLionsAreThere() + " of the species " + Lion.GENUS_SPECIES);

    }
}
