package org.thekennel;

public class Kennel {
    public static void main(String[] args) {
        Dog manny = new Dog(2,"Black and White", "pig");
        Object bentley = new Dog(2, "Black and White", "pig");

        System.out.println("manny is of class " + manny.getClass());
        System.out.println("bentley is of class " + bentley.getClass());
        if (manny == bentley) {
            System.out.println("Manny and bentley are shallow equal.");
        } else {
            System.out.println("Manny and bentley are NOT shallow equal.");
        }

        if (manny.equals(bentley)) {
            System.out.println("Manny and bentley are deep equal.");
        } else {
            System.out.println("Manny and bentley are NOT deep equal.");
        }

        Dog joseph = manny;

        if (manny == joseph) {
            System.out.println("Manny and joseph are shallow equal.");
        } else {
            System.out.println("Manny and joseph are NOT shallow equal.");
        }

        if (manny.equals(joseph)) {
            System.out.println("Manny and joseph are deep equal.");
        } else {
            System.out.println("Manny and joseph are NOT deep equal.");
        }

    }
}
