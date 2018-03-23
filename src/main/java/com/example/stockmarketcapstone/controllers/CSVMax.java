package com.example.stockmarketcapstone.controllers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CSVMax {

    public static String makeCapital(String name){
        return name.toUpperCase();
    }



    private static final String SAMPLE_CSV_FILE_PATH = "C:\\stocks\\csv-capstone-db\\data\\iris.csv";

    private static final String SAMPLE_CSV_FILE = "C:\\stocks\\csv-capstone-db\\data\\3-17-2018-results.csv";

    public static void main(String[] args) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());

                BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        //                       .withHeader("date", "record number","open close decrease","percentage decrease","word list"));
                        .withHeader("countries"));
        ) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            CSVRecord largestSepalLengthSoFar = null;

            CSVRecord smallestSepalLengthSoFar = null;

            CSVRecord largestSepalWidthSoFar = null;
            CSVRecord smallestSepalWidthSoFar = null;
            CSVRecord largestPetalLengthSoFar = null;
            CSVRecord smallestPetalLengthSoFar = null;
            CSVRecord largestPetalWidthSoFar = null;
            CSVRecord smallestPetalWidthSoFar = null;



            int count = 0;
            for (CSVRecord currentRow : csvRecords) {
                // If largestSoFar is nothing
                String speciesType = currentRow.get("species");

                if (speciesType.equals("setosa")){
                    count++;
                }
                if (largestSepalLengthSoFar ==null){
                    largestSepalLengthSoFar = currentRow;
                }

                if (smallestSepalLengthSoFar == null){
                    smallestSepalLengthSoFar = currentRow;
                }
                if (largestSepalWidthSoFar == null){
                    largestSepalWidthSoFar = currentRow;
                }
                if (smallestSepalWidthSoFar == null){
                    smallestSepalWidthSoFar = currentRow;
                }
                if (largestPetalLengthSoFar == null){
                    largestPetalLengthSoFar = currentRow;
                }
                if (smallestPetalLengthSoFar == null){
                    smallestPetalLengthSoFar = currentRow;
                }
                if (largestPetalWidthSoFar == null){
                    largestPetalWidthSoFar = currentRow;
                }
                if (smallestPetalWidthSoFar == null){
                    smallestPetalWidthSoFar = currentRow;
                }





                else {

                    double currentSepalLength = Double.parseDouble(currentRow.get("sepal_length"));
                    double largestSepalLength = Double.parseDouble(largestSepalLengthSoFar.get("sepal_length"));
                    double smallestSepalLength = Double.parseDouble(smallestSepalLengthSoFar.get("sepal_length"));
                    double currentSepalWidth = Double.parseDouble(currentRow.get("sepal_width"));
                    double largestSepalWidth = Double.parseDouble(largestSepalWidthSoFar.get("sepal_width"));
                    double smallestSepalWidth = Double.parseDouble(smallestSepalWidthSoFar.get("sepal_width"));
                    double currentPetalLength = Double.parseDouble(currentRow.get("petal_length"));
                    double largestPetalLength = Double.parseDouble(largestPetalLengthSoFar.get("petal_length"));
                    double smallestPetalLength = Double.parseDouble(smallestPetalLengthSoFar.get("petal_length"));
                    double currentPetalWidth = Double.parseDouble(currentRow.get("petal_width"));
                    double largestPetalWidth = Double.parseDouble(largestPetalWidthSoFar.get("petal_width"));
                    double smallestPetalWidth = Double.parseDouble(smallestPetalWidthSoFar.get("petal_width"));




                    if (currentSepalLength > largestSepalLength)
                        largestSepalLengthSoFar = currentRow;



                    if (currentSepalLength < smallestSepalLength)
                        smallestSepalLengthSoFar = currentRow;


                    if (currentSepalWidth > largestSepalWidth)
                        largestSepalWidthSoFar = currentRow;

                    if (currentSepalWidth < smallestSepalWidth)
                        smallestSepalWidthSoFar = currentRow;

                    if (currentPetalLength > largestPetalLength)
                        largestPetalLengthSoFar = currentRow;

                    if (currentPetalLength < smallestPetalLength)
                        smallestPetalLengthSoFar = currentRow;

                    if (currentPetalWidth > largestPetalWidth)
                        largestPetalWidthSoFar = currentRow;

                    if (currentPetalWidth < smallestPetalWidth)
                        smallestPetalWidthSoFar = currentRow;










                }


            }

            System.out.println("sepal length min  max   " + smallestSepalLengthSoFar.get("sepal_length")+ "  "+ largestSepalLengthSoFar.get("sepal_length") );

            System.out.println("sepal width min max     " + smallestSepalWidthSoFar.get("sepal_width")+ "  "+ largestSepalWidthSoFar.get("sepal_width") );

            System.out.println("petal length min max    "+ smallestPetalLengthSoFar.get("petal_length") + "  "+ largestPetalLengthSoFar.get("petal_length") );

            System.out.println("petal width min max     "+ smallestPetalWidthSoFar.get("petal_width")+ "  "+ largestPetalWidthSoFar.get("petal_width"));

        }

    }

}