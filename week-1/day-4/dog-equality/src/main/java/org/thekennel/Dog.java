package org.thekennel;

import java.util.Objects;

public class Dog {
    private int age;
    private String color;
    private String favoriteToy;

    private String shotRecord;

    public Dog(int age, String color, String favoriteToy) {
        this.age = age;
        this.color = color;
        this.favoriteToy = favoriteToy;
    }

    public void haveABirthday() {this.age++;}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFavoriteToy() {
        return favoriteToy;
    }

    public void setFavoriteToy(String favoriteToy) {
        this.favoriteToy = favoriteToy;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null) return false;
//        if (this.getClass() != o.getClass()) return false;
//        Dog dog = (Dog) o;
//        return this.getAge() == dog.getAge() &&
////                this.getAge().equals(dog.getAge()) &&
//                this.getColor().equals(dog.getColor()) &&
//                this.getFavoriteToy().equals(dog.getFavoriteToy());
//    }


    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                ", color='" + color + '\'' +
                ", favoriteToy='" + favoriteToy + '\'' +
                ", shotRecord='" + shotRecord + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return age == dog.age && Objects.equals(color, dog.color) && Objects.equals(favoriteToy, dog.favoriteToy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, color, favoriteToy);
    }
}
