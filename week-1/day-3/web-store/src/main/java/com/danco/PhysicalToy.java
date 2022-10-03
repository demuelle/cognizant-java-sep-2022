package com.danco;

public class PhysicalToy extends Product {
    private double weight;
    private String boxType;

    public boolean deliver() {
        System.out.println("Sending via UPS.");
        return true;
    }
}
