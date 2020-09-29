package me.syfe.school.holidayprogram.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import me.syfe.school.holidayprogram.HolidayProgram;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {
    public static List<List<String>> readCSV(String fileloc) throws IOException {
        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader(fileloc));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (CsvValidationException e) {
            e.printStackTrace();
            HolidayProgram.main(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            HolidayProgram.main(new String[0]);
        }
        return records;
    }

    public static void writeCSV(String[] array) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter("../booking-details.csv"));

        br.write(String.join(",", array));
        br.close();
    }
}
