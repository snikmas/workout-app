package com.blueprint.managers;

import com.blueprint.user.User;
import com.blueprint.utils.Utilities;

import static com.blueprint.utils.MenuItems.mainMenuBasicItems;

public class UIManager {
    // menu manager

    public void runMenu(User user){

        for(int i = 1; i <= mainMenuBasicItems.length; i++){
            System.out.println(i + ". " + mainMenuBasicItems[i]);
        }

        int input = Utilities.getIntInput(1, 7);


        return;
    }
}
