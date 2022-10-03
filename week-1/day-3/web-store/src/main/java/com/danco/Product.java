package com.danco;

public abstract class Product {
    private double price;
    private String name;
    private String description;

    public void sendInvoice() {
        System.out.println("Sending invoice for $" + price);
    }

    public abstract boolean deliver();
}
