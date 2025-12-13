package com.blueprint.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.blueprint.constants.PasswordError;
import com.blueprint.exceptions.NoPasswordInHashing;
import com.blueprint.user.User;

import java.util.Scanner;

public class Utilities {

    static Scanner scanner = new Scanner(System.in);
    public static int getIntInput(int from, int to){
        int input;
        while(true){
            System.out.print(">> ");
            if(scanner.hasNextInt()){
                input = scanner.nextInt();

                if(input <= to && input >= from){
                    return input;
                }
            System.out.println("Invalid input! Try again...\n");
        }
        }
    }

    public static char getYorN(){
        String input;
        while(true){
            System.out.print(">> ");
            if(scanner.hasNextLine()){
                input = scanner.nextLine().toUpperCase();
                if(input.equals("Y")){
                    return input.charAt(0);
                } else if(input.equals("N")){
                    return input.charAt(0);
                }
                System.out.println("Invalid input! Try again...");
            }
        }
    }

    public static String getStringInput(){
        String input;
        while(true){
            System.out.println(">> ");
            if(scanner.hasNextLine()){
               return scanner.nextLine();
            } else {
                System.out.println("Invalid input! Try again");
            }
        }
    }

    public static boolean isPasswordValid(String userInput){
        // rules: 8+ characters
        // 1+ 1+ uppercase + smallcase + 1 digit + 1spec character
        //
        if(userInput.length() < 8){
            System.out.println(PasswordError.TOO_SHORT.getMessage());
            return false;
        }
        if(!userInput.matches("\\d")) {
            System.out.println(PasswordError.NO_DIGIT.getMessage());
            return false;
        }
        if(!userInput.matches("\\?=[A-Z]")){
            System.out.println(PasswordError.NO_UPPERCASE.getMessage());
            return false;
        }
        if(!userInput.matches("\\?=[a-z]")){
            System.out.println(PasswordError.NO_LOWERCASE.getMessage());
            return false;
        }
        if (userInput.matches("\\\\p\\{ASCII}*")) {
            System.out.println(PasswordError.NO_ENGLISH.getMessage());
            return false;
        }
        return true;
    }

    public static String hashingPassword(String password){
        if(password == null) throw new NoPasswordInHashing("No password to the hashingPassword");
        // bcrypt accepts charArray or ByteArray
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        // verification
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);

        return null;
    }

}
