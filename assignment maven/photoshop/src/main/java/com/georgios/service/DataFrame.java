package com.georgios.service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;


public class DataFrame {
    
    private ArrayList<String> columns;
    private ArrayList<Integer> rowIndex; // Added row index
    private TreeMap<String, String> fields;

    public DataFrame(ArrayList<String> columns, ArrayList<Integer> rowIndex, TreeMap<String,String> fields) {
        this.columns = columns;
        this.rowIndex = rowIndex;
        this.fields = fields;
    }

    public DataFrame () {

        
    }

    // Getters and setters for columns, rowIndex, and fields

    public ArrayList<String> getColumns() {
        return this.columns;
    }

    public void setColumns(ArrayList<String> columns) {
        this.columns = columns;
    }

    public ArrayList<Integer> getRowIndex() {
        return this.rowIndex;
    }

    public void setRowIndex(ArrayList<Integer> rowIndex) {
        this.rowIndex = rowIndex;
    }

    public TreeMap<String,String> getFields() {

        if (this.fields.equals(null)) {
            TreeMap <String, String> bad = new TreeMap<>();
            return bad;
        } else {
        return this.fields;}
    }

    public void setFields(TreeMap<String,String> fields) {
        this.fields = fields;
    }

    public DataFrame populateDataFrame(String csvFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            // Read columns from the header line of the CSV file
            String headerLine = reader.readLine();
            String[] columns = headerLine.split(";");
            int numberOfColumns = columns.length;

            // Create placeholder for row index and columns
            ArrayList<String> columnsList = new ArrayList<>(Arrays.asList(columns));

            // Read rows and fields from the remaining lines of the CSV file
            String line;
            TreeMap<String, String> fields = new TreeMap<>();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                String row = values[0]; // Assuming the first column represents row identifiers (like row numbers)

                for (int i = 0; i < numberOfColumns; i++) {
                    String key = columns[i] + "_" + row;
                    String value = values[i];
                    fields.put(key, value);
                }
            }

            // Populate the data frame
            this.setColumns(columnsList);
            this.setRowIndex(rowIndex);
            this.setFields(fields);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
