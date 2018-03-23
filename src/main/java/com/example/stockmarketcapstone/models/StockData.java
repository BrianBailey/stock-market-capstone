package com.example.stockmarketcapstone.models;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class StockData {

    private static final String DATA_FILE = "djia-08082008-07012016.csv";
    private static boolean isDataLoaded = false;

    private static ArrayList<HashMap<String, String>> allStocks;


    public static ArrayList<String> findAll(String field) {

        // load data, if not already loaded
        loadData();

        ArrayList<String> values = new ArrayList<>();

        for (HashMap<String, String> row : allStocks) {
            String aValue = row.get(field);

            if (!values.contains(aValue)) {
                values.add(aValue);
            }
        }


        Collections.sort(values);

        return values;
    }

    public static ArrayList<HashMap<String, String>> findAll() {

        // load data, if not already loaded
        loadData();


        return new ArrayList<>(allStocks);
    }


    public static ArrayList<HashMap<String, String>> findByColumnAndValue(String column, String value) {

        // load data, if not already loaded
        loadData();

        ArrayList<HashMap<String, String>> stocks = new ArrayList<>();

        for (HashMap<String, String> row : allStocks) {

            String aValue = row.get(column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                stocks.add(row);
            }
        }

        return stocks;
    }


    public static ArrayList<HashMap<String, String>> findByValue(String value) {

        // load data, if not already loaded
        loadData();

        ArrayList<HashMap<String, String>> stocks = new ArrayList<>();

        for (HashMap<String, String> row : allStocks) {

            for (String key : row.keySet()) {
                String aValue = row.get(key);

                if (aValue.toLowerCase().contains(value.toLowerCase())) {
                    stocks.add(row);

                    
                    break;
                }
            }
        }

        return stocks;
    }

    /**
     * Read in data from a CSV file and store it in a list
     */
    private static void loadData() {

        // Only load data once
        if (isDataLoaded) {
            return;
        }

        try {

            // Open the CSV file and set up pull out column header info and records
            Resource resource = new ClassPathResource(DATA_FILE);
            InputStream is = resource.getInputStream();
            Reader reader = new InputStreamReader(is);
            CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            List<CSVRecord> records = parser.getRecords();
            Integer numberOfColumns = records.get(0).size();
            String[] headers = parser.getHeaderMap().keySet().toArray(new String[numberOfColumns]);

            allStocks = new ArrayList<>();

            // Put the records into a more friendly format
            for (CSVRecord record : records) {
                HashMap<String, String> newStock = new HashMap<>();

                for (String headerLabel : headers) {
                    newStock.put(headerLabel, record.get(headerLabel));
                }

                allStocks.add(newStock);
            }

            // flag the data as loaded, so we don't do it twice
            isDataLoaded = true;

        } catch (IOException e) {
            System.out.println("Failed to load stock data");
            e.printStackTrace();
        }
    }

}
