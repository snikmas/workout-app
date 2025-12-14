package com.blueprint.managers;

import com.blueprint.user.User;
import com.blueprint.utils.Utilities;
import com.blueprint.workout.Exercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static com.blueprint.utils.MenuItems.mainMenuBasicItems;

public class UIManager {
    Logger log = LoggerFactory.getLogger(UIManager.class.getSimpleName());

    // menu manager
    static Managers managers;
    public UIManager(Managers managers){
        this.managers = managers;
    }


    public void runMenu(User user) throws SQLException {

        while(true){
            System.out.println("Menu:");
            for(int i = 0; i < mainMenuBasicItems.length; i++){
                System.out.println(i  + 1 + ". " + mainMenuBasicItems[i]);
            }
            // if !user -> u can't open 0-2

            int input = Utilities.getIntInput(1, 7) - 1; //index as there
            if(user == null && input <= 2){
                // give him option to register
                System.out.println("You are not logged in. Do you have an account? [Y/N]");
                char doHaveAccount = Utilities.getYorN();
                if(doHaveAccount == 'Y'){
                    user = managers.getUIManager().signIn(user);
                    if(user != null){
                        System.out.println("Welcome back, " + user.getNickname() + "!");
                    } else {
                        System.out.println("The account not found");
                    }
                } else {
                    user = managers.getUIManager().signUp(user);
                    if(user != null){
                        System.out.println("Registration successful! You can now sign in.");
                    } else {
                        System.out.println("Registration failed. Please, try again...");
                    }
                }
                continue;
            }

            // count from 0 to 6!
            switch (input){
                case 1 -> {
                    System.out.println("Hii");
                }
                case 4 -> {
                    List<Exercise> res = showAllExercises();
                    if(res != null){
                        System.out.println("Do you want to see more details? [Y/N]");
                        char uInput = Utilities.getYorN();
                        if(uInput == 'Y') Utilities.showDetailedExerciseInfo(res);
                    }
                }
                case 6 -> {
                    return;
                }
            }
        }
    }

    // can show short info and full info
    public static List<Exercise> showAllExercises(){
        // 1. connect to the db
        // 2. get data
        // 3. show it
        List<Exercise> result = managers.getExerciseManager().getAllExercises();
        if(result == null){
            System.out.println("There are no exercises yet!");
            return null;
        }
        for(int i = 0; i < result.size(); i++){
            System.out.println((i + 1) + ". " + result.get(i).getTitle());
        }
        return result;
    }

    public User signUp(User user){
        // 1. get credentials
        // 2. check credentials (valid/unvalid)
        // 3. check if exists such kind of
        // 4. try to put to the db, check errors
        String nickname;
        String login;
        String email;
        String birthdayString;
        LocalDate birthdayDate;
        String password;
        // + created_at

        System.out.println("Input your nickname:");
        nickname = Utilities.getStringInput();
        if(nickname.isEmpty()){
            System.out.println("The nickname couldn't be empty! Try again...");
            nickname = Utilities.getStringInput();
        }

        System.out.println("Input your login:");
        login = Utilities.getStringInput();
        while(login.isEmpty() || !Utilities.isLoginValid(login).isEmpty()){
            System.out.println("Invalid login! Try again...");
            login = Utilities.getStringInput();
        }

        System.out.println("Input your email:");
        email = Utilities.getStringInput();
        while(email.isEmpty()){
            System.out.println("Invalid email! Try again...");
            email = Utilities.getStringInput();
        }

        System.out.println("Input your password:");
        password = Utilities.getStringInput();
        String msg = "";
        msg = Utilities.isPasswordValid(password);
        while(!msg.isEmpty()){
            System.out.println(msg + " Try again");
            password = Utilities.getStringInput();
        }

        System.out.println("Input your birthday [YYYY-MM-DD]:");
        birthdayString = Utilities.getStringInput();
        while(true){
            if(!Utilities.isBirthdayInputCorrect(birthdayString)){
                System.out.println("Invalid input. Try again");
                birthdayString = Utilities.getStringInput();
                continue;
            }
            birthdayDate = LocalDate.parse(birthdayString);
            if(birthdayDate.isAfter(LocalDate.now())){
                System.out.println("Invalid input. Try again");
                birthdayString = Utilities.getStringInput();
                continue;
            }
            break;
        }

        user = managers.getUserManager().signUp(nickname, login, email, password, birthdayDate);
        return user;
    }

    public User signIn(User user) throws SQLException {
        // have to try to find in the ui
        System.out.println("Sign in by:\n1 - Login\n2 - Email");
        int userInput = Utilities.getIntInput(1, 2);
        String password;
        String login = "";
        String email = "";

        switch (userInput) {
            case 1 -> {
                System.out.println("Input your login:");
                login = Utilities.getStringInput();

                while(login.isEmpty()){
                    System.out.println("Login could not be empty. Try again");
                    login = Utilities.getStringInput();
                }
            } case 2 -> {
                System.out.println("Input your email:");
                email = Utilities.getStringInput();
            }
        }

        System.out.println("Input your password:");
        password = Utilities.getStringInput();
        while (password.isEmpty()){ // we can let it write and later send
            System.out.println("Password could not be empty. Try again");
            password = Utilities.getStringInput();
        }

        if(!Utilities.isPasswordValid(password).isEmpty()){
            log.info("password is not valid: got the message from isPasswordValid");
            return null;
        }

        if(!login.isEmpty()){
            log.info("login is not empty: searching by login...");
            user = managers.getUserManager().signIn(login, password, false);
            if(!Utilities.isLoginValid(login).isEmpty()){
                log.info("login is not valid: got a message from isLoginValid");
                return null;
            }
        } else if(!email.isEmpty()){
            log.info("email is not empty: searching by email...");
            user = managers.getUserManager().signIn(email, password, true);
        }
        return user;
    }
}
