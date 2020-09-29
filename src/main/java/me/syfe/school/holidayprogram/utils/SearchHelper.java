package me.syfe.school.holidayprogram.utils;

import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

public class SearchHelper {
    public static Number isOriginExist(String[] origins, String choice){
        for(int i = 0; i < origins.length; i++){
            if(origins[i].equalsIgnoreCase(choice)){
                return i;
            }
        }
        return null;
    }

    public static Number isDestinationExist(String[] destinations, String choice){
        for(int i = 0; i < destinations.length; i++){
            if(destinations[i].equalsIgnoreCase(choice)){
                return i;
            }
        }
        return null;
    }

    public static int nightlyDestinationPrice(int index) throws IOException {
        List<List<String>> holidayDests = CSVHelper.readCSV("../holidayDestinations.csv");
        return parseInt(holidayDests.get(index).get(1));
    }

    public static Number getRealIndex(String origin, String destination, List<List<String>> airportDetails) {
        for(int i = 0; i < airportDetails.size(); i++){
            if(airportDetails.get(i).get(0).equalsIgnoreCase(origin) && airportDetails.get(i).get(1).equalsIgnoreCase(destination)){
                return i;
            }
        }
        return null;
    }
}
