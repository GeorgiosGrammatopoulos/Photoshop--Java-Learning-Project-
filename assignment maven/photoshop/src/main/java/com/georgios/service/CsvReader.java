  //the class that is going to do all the reading and writing from my csvs without populating items
package com.georgios.service;
import com.opencsv.exceptions.*;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;


public class CsvReader {       //class with csv readers, so that I never have to do that again

    private static void printRow(String[] row) {
        for (String cell : row) {
            System.out.printf("%-15s", cell); // Adjust spacing/formatting as needed
        }
        System.out.println();
    }

    
public static void printFullTable(String path) {
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] headers = reader.readNext();

            if (headers != null) {
                printRowWithSpaces(headers); // Print headers with spaces

                String[] line;
                while ((line = reader.readNext()) != null) {
                    printRowWithSpaces(line);
                }
            } else {
                System.out.println("Empty CSV file!");
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private static void printRowWithSpaces(String[] row) {
        for (int i = 0; i < row.length; i++) {
            String[] elements = row[i].split(";");
            for (int j = 0; j < elements.length; j++) {
                System.out.print(elements[j]);
                if (j != elements.length - 1) {
                    System.out.print("; "); // Add an additional space after each semicolon
                }
            }
            if (i != row.length - 1) {
                System.out.print(" "); // Add space between columns
            }
        }
        System.out.println();
    }

}