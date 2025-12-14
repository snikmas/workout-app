package com.blueprint;

import com.blueprint.managers.Managers;
import com.blueprint.managers.UIManager;
import com.blueprint.user.User;

import java.sql.SQLException;

import static java.awt.SystemColor.text;

public class Main {

    Managers managers = new Managers();
    UIManager uiManager = managers.getUIManager();


    public static void main(String[] args) throws SQLException {


        Main main = new Main();
        // run from here
        main.run();
    }

    public void run() throws SQLException {
        User user = null;
        uiManager.runMenu(user);
    }
}