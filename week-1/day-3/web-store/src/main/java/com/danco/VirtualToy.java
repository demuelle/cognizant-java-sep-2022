package com.danco;

public class VirtualToy extends Product {
    private int fileSize;

    public boolean deliver() {
        System.out.println("Sending via Google Cloud to the app...");
        // call method that pushes file to client's tablet app
        boolean success = true;
        if (success) {
            System.out.println("This virtual toy was delivered!");
            sendInvoice();
        } else {
            System.out.println("Failed to deliver virtual toy.");
        }
        return success;
    }
}
