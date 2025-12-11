package com.blueprint;

import com.blueprint.managers.Managers;
import com.blueprint.managers.UIManager;
import com.blueprint.user.User;

import static java.awt.SystemColor.text;

public class Main {

    Managers managers = new Managers();
    UIManager uiManager = managers.getUIManager();

    public static void main(String[] args) {


        Main main = new Main();
        // run from here
        while(true){
            main.run();
            break;
        }
    }

    public void run(){
        User user = null;
        uiManager.runMenu(user);
    }
}