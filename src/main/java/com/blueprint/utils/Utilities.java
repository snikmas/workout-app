package com.blueprint.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.blueprint.constants.LoginError;
import com.blueprint.constants.PasswordError;
import com.blueprint.exceptions.HashingError;
import com.blueprint.exceptions.NoPasswordInHashing;
import com.blueprint.user.User;

import java.sql.ResultSet;
import java.util.Scanner;

public class Utilities {

    static Scanner scanner = new Scanner(System.in);

    // no problems
    public static int getIntInput(int from, int to){
        int input;
        while(true){
            System.out.print(">> ");
            if(scanner.hasNextInt()){
                input = scanner.nextInt();
                scanner.nextLine(); // takes \n
                if(input <= to && input >= from){
                    return input;
                }
        }
            System.out.println("Invalid input! Try again...\n");
        }
    }

    public static char getYorN(){
        String input;
        while(true){
            System.out.print(">> ");
            input = scanner.nextLine().toUpperCase();

            if(input.equals("Y") || input.equals("N")){
                return input.charAt(0);
            }
            System.out.println("Invalid input! Try again...");
        }
    }


    public static String getStringInput(){
        String input;
        while(true){
            System.out.print(">> ");
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
        if(!userInput.matches(".*\\d.*")) {
            System.out.println(PasswordError.NO_DIGIT.getMessage());
            return false;
        }
        if(!userInput.matches(".*[A-Z].*")){
            System.out.println(PasswordError.NO_UPPERCASE.getMessage());
            return false;
        }
        if(!userInput.matches(".*[a-z].*")){
            System.out.println(PasswordError.NO_LOWERCASE.getMessage());
            return false;
        }
        if (!userInput.matches("\\p{ASCII}*")) {
            System.out.println(PasswordError.NO_ENGLISH.getMessage());
            return false;
        }
        return true;
    }

    public static String hashingPassword(String password){
        if(password == null) throw new NoPasswordInHashing("No password to the hashingPassword");
        // bcrypt accepts charArray or ByteArray
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        // verification that string and hashed are the same. do we really need it?
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);

        if(result.verified) return bcryptHashString;
        else throw new HashingError("Error during verification a password!");
    }


    public static User mapUser(ResultSet res){
        User user = new User();
        try {
            if(!res.next()) return null;
            while(res.next()){
                user.setLogin(res.getString("login"));
                user.setEmail(res.getString("email"));
                user.setBirthday(res.getDate("birthday").toLocalDate());
                user.setNickname(res.getString("nickname"));
                user.setPassword(res.getString("password_hash"));
                user.setCreated_at(res.getDate("created_at").toLocalDate());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static boolean isBirthdayInputCorrect(String birthdayString){
        return birthdayString.matches("^\\d{4}-\\d{2}-\\d{2}\\b") ;
    }

    // some bugs, later should fix it`
    public static boolean isLoginValid(String login){
        if(login.length() < 3 || login.length() > 20){
            System.out.println(LoginError.INVALID_LENGTH.getMessage());
        } else if(!login.matches(".*[A-Za-z].*")){
            System.out.println(LoginError.NO_LETTERS.getMessage());
        } else if (login.contains("---") || login.contains("___") || login.contains("...")){
            System.out.println(LoginError.CONSECUTIVE_CHARACTERS.getMessage());
        } else if(!login.matches("[a-zA-Z0-9._-]+")){
            System.out.println(LoginError.INVALID_CHARACTER.getMessage());
        } else {
            return true;
        }
        return false;
    }

}
