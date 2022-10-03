package com.company;

import java.util.*;

public class ListExercises {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        List<String> cities = Arrays.asList("Chicago", "Denver", "Tampa",  "Los Angeles");

        System.out.println("The number of names in my list is " + names.size());

        names.add("Austin");
        System.out.println("The number of names in my list is " + names.size());

        String firstNameInTheList = names.get(0);
        System.out.println("The first name in the list is " + firstNameInTheList);
        
        names.add("Yi");
        names.add("Andrew");
        names.add("Mari");
        System.out.println("The number of names in my list is " + names.size());

        System.out.println("Element 2 is " + names.get(2));
        System.out.println("Element 1 is " + names.get(1));

        names.remove(0);
        System.out.println("Now there are " + names.size() + " names in the list.");
        System.out.println("Now, the first name in the list is " + names.get(0));

        names.clear();
        System.out.println("Now there are " + names.size() + " names in the list.");

        names.add("Austin");
        names.add("Yi");
        names.add("Andrew");
        names.add("Mari");
        names.add("Tri");
        names.add("JB");

        System.out.println("Now there are " + names.size() + " names in the list.");

        for(String aName : names) {
            System.out.println("My favorite students is " + aName);
        }

        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i) + " gets an A!");
        }

        Iterator<String> iterator = names.iterator();
        while(iterator.hasNext()) {
            String personsName = iterator.next();
            System.out.println(personsName + " worked all weekend!");
            System.out.println("Get some sleep, " + personsName);
        }

        List<Integer> everyonesAges = new ArrayList<>();
        Integer integer1 = new Integer(31);
        everyonesAges.add(integer1);
        everyonesAges.add(new Integer(24));
        everyonesAges.add(new Integer("45"));
        everyonesAges.add(10);

        int thisAge = 0;
        for(Integer i: everyonesAges) {
            thisAge = i;
            System.out.println(thisAge);
        }

        int aNumber = everyonesAges.get(1);
        System.out.println(aNumber);
        // autoboxing ==> automatically convert from primitive to wrapper-class version
        // unboxing ==> automatically convert from wrapper-class version to primitive

        List<Employee> workforce = new ArrayList<>();
        workforce.add(new Employee("Sammy", "food service"));
        workforce.add(new Employee("Stephanie", "snark"));

        List<Employee> biggerCompany = new ArrayList<>();
        biggerCompany.add(new Employee("Colin", "roasting"));
        biggerCompany.add(new Employee("Richard", "dusting"));
        Employee nick = new Employee("Nick", "Executive");
        biggerCompany.add(nick);

        System.out.println("workforce has " + workforce.size() + " employees");
        System.out.println("biggerCompany has " + biggerCompany.size() + " employees");

        System.out.println("Merger!!!");
        biggerCompany.addAll(workforce);
        System.out.println("now, biggerCompany has " + biggerCompany.size() + " employees");

        for (Employee e : biggerCompany) {
            System.out.println(e + " works here!");
        }

        nick.setDepartment("deity");

        System.out.println("REORG! After the reorg: ");
        for (Employee e : biggerCompany) {
            System.out.println(e + " works here!");
            if (e.getName().equals("Colin")) {
                e.setDepartment("baking and roasting");
            }
        }

        System.out.println("ANOTHER REORG! After the reorg: ");
        for (Employee e : biggerCompany) {
            System.out.println(e + " works here!");
        }

        // in a test, I can compare equality of lists with assertEquals


    }
}
