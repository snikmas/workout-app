package com.blueprint.utils;

import com.blueprint.user.User;

import java.util.Scanner;

public class Utilities {

    static Scanner scanner = new Scanner(System.in);
    public static int getIntInput(int from, int to){
        int input;
        while(true){
            System.out.println(">> ");
            if(scanner.hasNextInt()){
                input = scanner.nextInt();

                if(input <= to && input >= from){
                    return input;
                }
            System.out.println("Invalid input! Try again...\n");
        }
        }
    }


}
