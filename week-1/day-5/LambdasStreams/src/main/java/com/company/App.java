package com.company;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        try {

            List<Motorcycle> bikes = MotoFileIO.getMotorcycles("motorcycles.csv");

            // Print the information for all Suzuki motorcycles in inventory
            String make = "Suzuki";
            System.out.println("All " + make + " motorcycles in inventory: ");
            bikes
                    .stream()
                    .filter(b -> b.getMake().equals(make))
                    .forEach(x -> {
                        System.out.println("===============");
                        System.out.println("Make: " + x.getMake());
                        System.out.println("Model: " + x.getModel());
                        System.out.println("Color: " + x.getColor());
                        System.out.println("Year: " + x.getYear());
                        System.out.println("Displacement: " + x.getDisplacement());
                        System.out.println("Horsepower: " + x.getHorsepower());
                        System.out.println("Weight: " + x.getWeight());
                    });

            // Print the information for all motorcycles that weigh less than 500 pounds
            List<Motorcycle> anotherList = new ArrayList<>();
            System.out.println("Bikes that weight less than 500 pounds:");
            bikes
                    .stream()
                    .filter(moto -> moto.getWeight() < 500)
                    .forEach(x -> {
                        anotherList.add(x);
                        System.out.println("===============");
                        System.out.println("Make: " + x.getMake());
                        System.out.println("Model: " + x.getModel());
                        System.out.println("Color: " + x.getColor());
                        System.out.println("Year: " + x.getYear());
                        System.out.println("Displacement: " + x.getDisplacement());
                        System.out.println("Horsepower: " + x.getHorsepower());
                        System.out.println("Weight: " + x.getWeight());
                    });

            // Rather than printing out the matching motorcycles, let's put them into a list
            System.out.println("Save light bikes in a list");
            List<Motorcycle> lightBikes =
                bikes
                        .stream()
                        .filter(moto -> moto.getWeight() < 500)
                        .collect(Collectors.toList());

            System.out.println("There are " + lightBikes.size() + " light bikes.");
            System.out.println("Generated in for loop: " + anotherList.size() + " light bikes.");


            // We can also group our motorcycles by Make.
            Map<String, List<Motorcycle>> motorcylesByMake = bikes
                    .stream()
                    .collect(Collectors.groupingBy(moto -> moto.getMake()));

            Set<String> keys = motorcylesByMake.keySet();
            for (String thisMake : keys) {
                System.out.println(thisMake + " has these bikes: " + motorcylesByMake.get(thisMake));
            }

            // Print the average horsepower of the motorcycles in inventory
            double averageHorsepower = bikes
                    .stream()
                    .mapToInt(b -> b.getHorsepower())
                    .average()
                    .getAsDouble();

            System.out.println("The average horsepower of these motorcycles is " + averageHorsepower);

            int maxHorsePower = bikes
                    .stream()
                    .mapToInt(b -> b.getHorsepower())
                    .max()
                    .getAsInt();

            System.out.println("The maximum horsepower of these motorcycles is " + maxHorsePower);

//            int maxHorsePower2 = bikes
//                    .stream()
//                    .filter(bike -> bike.getWeight() > 3000)
//                    .mapToInt(b -> b.getHorsepower())
//                    .max()
//                    .getAsInt();

            Optional<Motorcycle> optionalMotorcycle = Optional.of(bikes.get(0));
            if (optionalMotorcycle.isPresent()) {
                System.out.println("Motorcycle found! " + optionalMotorcycle.get());
            } else {
                System.out.println("No motorcycle in this optionatl.");
            }

//            System.out.println("The max horsepower is " + maxHorsePower2.getAsInt());



        } catch (FileNotFoundException e) {
            System.out.println("Could not find CSV file: " + e.getMessage());
        }
    }

//    public boolean getMotorcyclesThatWeighLessThan500Pounds(Motorcycle moto) {
//        return moto.getWeight() < 500;
//    }

}
