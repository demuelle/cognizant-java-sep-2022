package edu.smu;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        List<String> file1Contents = new ArrayList<>();
        file1Contents.add("Hello, world!");
        file1Contents.add("I'm writing to a file!");
        file1Contents.add("!!!!!!!!");
        String file1Path = "/Home/foo/myFavoriteFile.txt";

//        try {
//            writeToFile(file1Path, file1Contents);
//        } catch (IOException ex) {
//            System.out.println("IOException writing to " + file1Path + ": " + ex.getMessage());
//        }
          writeToFile(file1Path, file1Contents);
    }

    public static void writeToFile(String filename, List<String> lines) throws IOException {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));

            for (String line : lines) {
                writer.println(line);
            }

            writer.flush();
            writer.close();

    }
}
