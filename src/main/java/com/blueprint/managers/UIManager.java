package com.blueprint.managers;

import com.blueprint.user.User;
import com.blueprint.utils.Utilities;

import static com.blueprint.utils.MenuItems.mainMenuBasicItems;

public class UIManager {
    // menu manager
    Managers managers;
    public UIManager(Managers managers){
        this.managers = managers;
    }

    public void runMenu(User user){

        System.out.println("Menu:");
        for(int i = 0; i < mainMenuBasicItems.length; i++){
            System.out.println(i  + 1 + ". " + mainMenuBasicItems[i]);
        }
        // if !user -> u can't open 0-2

        int input = Utilities.getIntInput(1, 7) - 1; //index as there
        if(user == null && input <= 2){
            // give him option to register
            System.out.println("You haven't been signed in yet. Do you have an account? Y/N");
            char doHaveAccount = Utilities.getYorN();
            if(doHaveAccount == 'Y'){
                user = singIn(user);
            } else{
                user = signUp(user);
            }
            return;
        }

    }

    public User signIn(User user) {
        // have to try to find in the ui
        System.out.println("Sign in by:\n1 - Login\n2 - email");
        int userInput = Utilities.getIntInput(1, 2);
        String password;
        String login = "";
        String email = "";

        switch (userInput) {
            case 1 -> {
                System.out.println("Input your login\n>> ");
                login = Utilities.getStringInput();
            } case 2 -> {
                System.out.println("Input your email\n>> ");
                email = Utilities.getStringInput();
            }
        }


        System.out.println("Input your password\n>> ");
        password = Utilities.getStringInput();
        while (!Utilities.isPasswordValid(password)){
            System.out.println("Invalid password! Try again");
            password = Utilities.getStringInput();
        }

        if(!login.isEmpty()){
            user = managers.getUserManager().signIn(login, password, false);
        } else if(!email.isEmpty()){
            user = managers.getUserManager().signIn(email, password, true);
        }


    }


}
