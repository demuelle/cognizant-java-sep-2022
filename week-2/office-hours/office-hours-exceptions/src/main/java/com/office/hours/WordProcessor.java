package com.office.hours;

import com.office.hours.exceptions.FileTooLargeException;

public class WordProcessor {
    public static void main(String[] args) throws Exception {
        WrittenDocument doc1;
        try {
            int i =1;
            doc1 = new WrittenDocument("my-first-doc.txt");
            doc1.addText("Line " + i++ + ": Hello, World of Docs!\n");
            doc1.addText("Line " + i++ + ": Hello, World of Docs!\n");
            doc1.addText("Line " + i++ + ": Hello, World of Docs!\n");
            System.out.println(doc1.getText());
            doc1.addText("Line " + i++ + ": Hello, World of Docs!\n");
            System.out.println("We made it here.");
            System.out.println(doc1.getText());
        } catch (FileTooLargeException rex) {
            System.out.println("Problem processing file " + rex.getMessage());
        } catch (Exception myException) {
            System.out.println("Couldn't create document: " + myException.getCause());
        }
//        int i = 1;
//        } catch (RuntimeException rex) {
//            System.out.println("Couldn't add file name to body! " + rex.getMessage());
//        }
//
//        System.out.println("The program is still running healthily.");
//
    }
}
