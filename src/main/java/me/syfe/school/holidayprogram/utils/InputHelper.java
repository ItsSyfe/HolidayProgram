package me.syfe.school.holidayprogram.utils;

import java.util.Scanner;

public class InputHelper {
    public static String getUserInput(String question){
        Scanner sc= new Scanner(System.in);
        System.out.print(question + "\n");
        String str = sc.nextLine();
        return str;
    }
}
