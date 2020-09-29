package me.syfe.school.holidayprogram.utils;

import me.syfe.school.holidayprogram.HolidayProgram;

import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MenuHelper {
    public static void mainMenu(List<List<String>> airportDetails, String[] origins, String [] destinations, int[] returnTripPrices) throws IOException, InterruptedException {
        String choiceO = MenuHelper.origin(origins); // origin location
        Number indexOfOrigin = SearchHelper.isOriginExist(origins, choiceO); // origin location

        String choiceD = MenuHelper.destinations(destinations); // destination location
        Number indexOfDestinations = SearchHelper.isDestinationExist(destinations, choiceD); // destination location

        Number realIndex = SearchHelper.getRealIndex(choiceO, choiceD, airportDetails);

        if(!realIndex.equals(null)){
            int returnPrice = returnTripPrices[(int) realIndex];
            if(!indexOfOrigin.equals(null)) {
                int indexOLoc = (Integer) indexOfOrigin;
                if(!indexOfDestinations.equals(null)){
                    int indexDLoc = (Integer) indexOfDestinations;
                    int nightPrice = SearchHelper.nightlyDestinationPrice(indexDLoc);
                    int nightsToStay = parseInt(InputHelper.getUserInput("How many nights are you planning to stay?"));

                    String[] forCSVWriting = new String[6];

                    forCSVWriting[0] = origins[indexOLoc];
                    forCSVWriting[1] = destinations[indexDLoc];
                    forCSVWriting[2] = Integer.toString(returnPrice);
                    forCSVWriting[3] = Integer.toString(nightPrice);
                    forCSVWriting[4] = Integer.toString(nightsToStay);
                    forCSVWriting[5] = Integer.toString((returnPrice+(nightPrice*nightsToStay)));

                    CSVHelper.writeCSV(forCSVWriting);

                    System.out.println("--------------------\nOrigin: " + origins[indexOLoc] + "\nDestination: " + destinations[indexDLoc] + "\nReturn price: " + returnPrice + "\nNightly price: " + nightPrice + "\nTotal nights stay: " + nightsToStay + "\nTotal Price: " + (returnPrice+(nightPrice*nightsToStay)) + "\n--------------------");

                    System.out.println("\nRe-opening program\n");

                    Thread.sleep(5000);

                    clearScreen();

                    HolidayProgram.main(new String[0]);
                } else {
                    System.out.println("Make sure you have a valid destination");
                    mainMenu(airportDetails, origins, destinations, returnTripPrices);
                }

            } else {
                System.out.println("Make sure you have a valid origin");
                mainMenu(airportDetails, origins, destinations, returnTripPrices);
            }
        } else {
            System.out.println("Unknown error, please try again");
            mainMenu(airportDetails, origins, destinations, returnTripPrices);
        }
    }

    public static String origin(String[] origins){
        String singleOrigins = "";

        for(int i = 0; i < origins.length; i++){
            if(singleOrigins.contains(origins[i])) continue;
            singleOrigins += origins[i] + ",";
        }

        String[] tmp = singleOrigins.split(",");

        String tbr = String.join(", ", tmp);

        String choice = InputHelper.getUserInput("Choose your origin\n" + tbr);

        return choice;
    }

    public static String destinations(String[] destinations){
        String singleDestinations = "";

        for(int i = 0; i < destinations.length; i++){
            if(singleDestinations.contains(destinations[i])) continue;
            singleDestinations += destinations[i] + ",";
        }

        String[] tmp = singleDestinations.split(",");

        String tbr = String.join(", ", tmp);

        String choice = InputHelper.getUserInput("Choose your destination\n" + tbr);

        return choice;
    }

    public static void clearScreen() {
        try
        {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
}
