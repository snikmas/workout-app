package com.blueprint.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.blueprint.constants.*;
import com.blueprint.exceptions.HashingError;
import com.blueprint.exceptions.NoPasswordInHashing;
import com.blueprint.user.User;
import com.blueprint.workout.Exercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Utilities {

    static Logger log = LoggerFactory.getLogger(Utilities.class.getSimpleName());
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

    public static String isPasswordValid(String userInput){
        // rules: 8+ characters
        // 1+ 1+ uppercase + smallcase + 1 digit + 1spec character
        //
        if(userInput.length() < 8){
            return PasswordError.TOO_SHORT.getMessage();
        }
        if(!userInput.matches(".*\\d.*")) {
            return PasswordError.NO_DIGIT.getMessage();
        }
        if(!userInput.matches(".*[A-Z].*")){
            return PasswordError.NO_UPPERCASE.getMessage();
        }
        if(!userInput.matches(".*[a-z].*")){
            return PasswordError.NO_LOWERCASE.getMessage();
        }
        if (!userInput.matches("\\p{ASCII}*")) {
            return PasswordError.NO_ENGLISH.getMessage();
        }
        return "";
    }

    public static String hashingPassword(String password){
        if(password == null) throw new NoPasswordInHashing("No password to the hashingPassword");
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());

    }

    public static Exercise mapExercise(ResultSet res){
        Exercise exercise = null;
        try {
            if(res.next()){
                exercise = new Exercise();
                exercise.setTitle(res.getString("title"));
                exercise.setDescription(res.getString("description"));
                exercise.setDifficulty(Difficulty.valueOf(res.getString("difficulty").toUpperCase()));
                Array categoryArr = res.getArray("category");
                Category[] catArr = categoryArr == null ?
                        new Category[0] : Arrays.stream((String[]) categoryArr.getArray())
                        .map(String::toUpperCase)
                        .map(Category::valueOf)
                        .toArray(Category[]::new);

                exercise.setCategory(catArr);
                Array musclesArr = res.getArray("musclegroups");
                MuscleGroup[] musArr = musclesArr == null ?
                        new MuscleGroup[9] : Arrays.stream((String[]) musclesArr.getArray())
                        .map(String::toUpperCase)
                        .map(MuscleGroup::valueOf)
                        .toArray(MuscleGroup[]::new);

                exercise.setMuscleGroup(musArr);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exercise;
    }


    public static User mapUser(ResultSet res){
        User user = null;
        try {
            if(res.next()) {
                user = new User();
                log.info("the res is not null");

                user.setLogin(res.getString("login"));
                user.setEmail(res.getString("email"));
                user.setBirthday(res.getDate("birthday").toLocalDate());
                user.setNickname(res.getString("nickname"));
                user.setPassword(res.getString("password_hash"));
                user.setCreated_at(res.getDate("created_at").toLocalDate());
            } else {
                log.info("No results");
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
    public static String isLoginValid(String login){
        if(login.length() < 3 || login.length() > 20){
            return LoginError.INVALID_LENGTH.getMessage();
        } else if(!login.matches(".*[A-Za-z].*")){
            return LoginError.NO_LETTERS.getMessage();
        } else if (login.contains("---") || login.contains("___") || login.contains("...")){
            return LoginError.CONSECUTIVE_CHARACTERS.getMessage();
        } else if(!login.matches("[a-zA-Z0-9._-]+")){
            return LoginError.INVALID_CHARACTER.getMessage();
        }
        // "" - no errors
        return "";
    }

    public static void showDetailedExerciseInfo(List<Exercise> res){
        for(int i = 0; i < res.size(); i++){
            Exercise ex = res.get(i);
            System.out.println((i + 1) + ". " + ex.getTitle());
            System.out.println("Description: " + ex.getDescription());
            System.out.println("Difficultly: " + ex.getDifficulty());
            System.out.println("Categories: ");
            for(Category cat : ex.getCategory()){
                System.out.println("- " + cat);
            }
            System.out.println("Muscle Groups: ");
            for(MuscleGroup mg : ex.getMuscleGroup()){
                System.out.println("- " + mg);
            }
            System.out.print("\n");


        }
    }

}
