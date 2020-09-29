package me.syfe.school.holidayprogram;

import me.syfe.school.holidayprogram.utils.CSVHelper;
import me.syfe.school.holidayprogram.utils.MenuHelper;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;
import static me.syfe.school.holidayprogram.utils.MenuHelper.clearScreen;


public class HolidayProgram {
    public static void main(String[] args) throws IOException {
        List<List<String>> airportDetails = CSVHelper.readCSV("../airportDetails.csv");
        String[] origins = new String[airportDetails.size()];
        String[] destinations = new String[airportDetails.size()];
        int[] returnTripPrices = new int[airportDetails.size()];

        for(int i = 0; i < airportDetails.size(); i++) {
            origins[i] = airportDetails.get(i).get(0);
            destinations[i] = airportDetails.get(i).get(1);
            returnTripPrices[i] = parseInt(airportDetails.get(i).get(2));
        }

        try{
            MenuHelper.mainMenu(airportDetails, origins, destinations, returnTripPrices);
        } catch(Exception e){
            clearScreen();
            System.out.println("\nPlease make sure you provide the correct details (this error catcher is so ez!)\n");
            HolidayProgram.main(new String[0]);
        }
    }
}
