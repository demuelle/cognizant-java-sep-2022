package com.company;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.List;

public class MotoFileIO {

    static List<Motorcycle> getMotorcycles(String filename) throws FileNotFoundException {
        return new CsvToBeanBuilder<Motorcycle>(new FileReader(filename)).withType(Motorcycle.class).build().parse();
    }

    static void writeMotorcycles(List<Motorcycle> motorcycles, String filename) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        // Now let's write the motorcycle list to another file
        Writer writer = new FileWriter(filename);
        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
        beanToCsv.write(motorcycles);
        writer.close();
    }
}
