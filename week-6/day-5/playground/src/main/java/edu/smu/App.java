package edu.smu;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Optional<String> amazonBox = Optional.of("toilet bowl cleaner");
        LocalDateTime someTime = LocalDateTime.of(2022, 11, 7, 9, 46, 0);

        Playground playground1 = new Playground(Arrays.asList("slide", "teeter totter"), "DMMemorial", 3);
        SchoolPlayground playground2 = new SchoolPlayground("k-6", "Lincoln Elementary", Arrays.asList("twisty slide", "steep slide", "jungle gym"), "Loretta Lynn Play Area");

        System.out.println("We have 2 playgrounds!");
        System.out.println("Playground 1 is a regular playground, and its name is " + playground1.getParkName());
        System.out.println("Playground 2 is a school playground for the school " + playground2.getSchoolName());
        System.out.println("The equipment at playground1 is " + playground1.getEquipmentString());
        System.out.println("The equipment at playground2 is " + playground2.getEquipmentString());

        sendKidToPlayground(playground1, "Little D9anny");
        sendKidToPlayground(playground2, "Little Sammy");
    }

    public static void sendKidToPlayground(Playground playground, String kid) {
        System.out.println("Wheeee! " + kid + " went to " + playground.getParkName() + "!");
    }
}
