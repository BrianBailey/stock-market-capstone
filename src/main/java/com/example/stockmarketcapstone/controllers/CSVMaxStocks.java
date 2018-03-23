package com.example.stockmarketcapstone.controllers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CSVMaxStocks {

    public static String makeCapital(String name){
        return name.toUpperCase();
    }



    private static final String SAMPLE_CSV_FILE_PATH = "C:\\stocks\\csv-capstone-db\\data\\djia-08082008-07012016.csv";

    private static final String SAMPLE_CSV_FILE = "C:\\stocks\\csv-capstone-db\\data\\3-22-2018-stock-results.csv";

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

            CSVRecord largestOpenSoFar = null;

            CSVRecord smallestOpenSoFar = null;

            CSVRecord largestAdjCloseSoFar = null;
            CSVRecord smallestAdjCloseSoFar = null;
            CSVRecord largestHighSoFar = null;
            CSVRecord smallestHighSoFar = null;
            CSVRecord largestVolumeSoFar = null;
            CSVRecord smallestVolumeSoFar = null;



            int count = 0;
            int countTotal = 0;
            for (CSVRecord currentRow : csvRecords) {
                // If largestSoFar is nothing
                //String dateTarget = currentRow.get("date");




                if (largestOpenSoFar ==null){
                    largestOpenSoFar = currentRow;
                }

                if (smallestOpenSoFar == null){
                    smallestOpenSoFar = currentRow;
                }
                if (largestAdjCloseSoFar == null){
                    largestAdjCloseSoFar = currentRow;
                }
                if (smallestAdjCloseSoFar == null){
                    smallestAdjCloseSoFar = currentRow;
                }
                if (largestHighSoFar == null){
                    largestHighSoFar = currentRow;
                }
                if (smallestHighSoFar == null){
                    smallestHighSoFar = currentRow;
                }
                if (largestVolumeSoFar == null){
                    largestVolumeSoFar = currentRow;
                }
                if (smallestVolumeSoFar == null){
                    smallestVolumeSoFar = currentRow;
                }





                else {

                    double currentOpen = Double.parseDouble(currentRow.get("open"));
                    double largestOpen = Double.parseDouble(largestOpenSoFar.get("open"));
                    double smallestOpen = Double.parseDouble(smallestOpenSoFar.get("open"));
                    double currentAdjClose = Double.parseDouble(currentRow.get("adjClose"));
                    double largestAdjClose = Double.parseDouble(largestAdjCloseSoFar.get("adjClose"));
                    double smallestAdjClose = Double.parseDouble(smallestAdjCloseSoFar.get("adjClose"));
                    double currentHigh = Double.parseDouble(currentRow.get("high"));
                    double largestHigh = Double.parseDouble(largestHighSoFar.get("high"));
                    double smallestHigh = Double.parseDouble(smallestHighSoFar.get("high"));
                    double currentVolume = Double.parseDouble(currentRow.get("volume"));
                    double largestVolume = Double.parseDouble(largestVolumeSoFar.get("volume"));
                    double smallestVolume = Double.parseDouble(smallestVolumeSoFar.get("volume"));

                    String currentDate = currentRow.get("date");
                    double currentLow = Double.parseDouble(currentRow.get("low"));
                    double currentClose = Double.parseDouble(currentRow.get("close"));
                    String currentLabel = currentRow.get("label");

                    if (currentLabel.equals("1")){
                        count++;
                    }

                    if (currentLabel.equals("1") || currentLabel.equals("0")){
                        countTotal++;
                    }










                    if (currentOpen > largestOpen)
                        largestOpenSoFar = currentRow;




                    if (currentOpen < smallestOpen)
                        smallestOpenSoFar = currentRow;



                    if (currentAdjClose > largestAdjClose)
                        largestAdjCloseSoFar = currentRow;

                    if (currentAdjClose < smallestAdjClose)
                        smallestAdjCloseSoFar = currentRow;



                    if (currentHigh > largestHigh)
                        largestHighSoFar = currentRow;

                    if (currentHigh < smallestHigh)
                        smallestHighSoFar = currentRow;



                    if (currentVolume > largestVolume)
                        largestVolumeSoFar = currentRow;
                    if (currentVolume < smallestVolume)
                        smallestVolumeSoFar = currentRow;













                }


            }

            System.out.println("There were "+ count+ " out of " + countTotal + " days,  that were up or neutral in this time period");
            System.out.println();
            System.out.println("open        min max    " + smallestOpenSoFar.get("open")+ "  "+ largestOpenSoFar.get("open") );

            System.out.println("adj Close   min max    " + smallestAdjCloseSoFar.get("adjClose")+ "  "+ largestAdjCloseSoFar.get("adjClose") );

            System.out.println("high        min max    "+ smallestHighSoFar.get("high") + "  "+ largestHighSoFar.get("high") );

            System.out.println("volume      min max    "+ smallestVolumeSoFar.get("volume")+ "      "+ largestVolumeSoFar.get("volume"));

        }

    }

}