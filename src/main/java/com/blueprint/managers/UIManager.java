package com.blueprint.managers;

import com.blueprint.user.User;

import static com.blueprint.utils.MenuItems.mainMenuBasicItems;
import static com.blueprint.utils.MenuItems.mainMenuUser;

public class UIManager {
    // menu manager

    public void runMenu(User user){

        if(user != null){
            for(String item : mainMenuUser){
                System.out.println(">> " + item);
            }
        }

        for(String item : mainMenuBasicItems){
            System.out.println(">> " + item);
        }


        return;
    }
}
