package com.wild;

public class Lion {

    private String name;
    private int age;
    private int weight;

    private static int lionCount = 0;
    public static final String GENUS_SPECIES = "Catus Lionitus";

    public Lion(String name, int age, int weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;

        lionCount++;
    }

    public static int howManyLionsAreThere() {
        return lionCount;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String generateDescription() {
        return this.name + ": a Lion that weighs " + this.weight + " pounds and is " + this.age + " years old.";
    }

    public void pounce() {
        System.out.println(this.name + " pounced!");
    }

    public void roar() {
        if (this.age < 1) {
            System.out.println("waw");
        } else if (this.age < 3) {
            System.out.println("Roar.");
        } else {
            System.out.println("ROOOOOOOOOOAAR!");
        }
    }

    public void eat(int foodWeight) {
        System.out.println(this.name + " found food!");
        this.weight = this.weight + foodWeight;
    }
}
