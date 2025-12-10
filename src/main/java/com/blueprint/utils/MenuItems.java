package com.blueprint.utils;

public class MenuItems {
    // if user == null -> write signup/sign in;
    //otherwise -> check your own
    public static String[] mainMenuBasicItems = {
            "Top Workouts",
            "All exercises",
            "All Workouts",
            "Exit"
    };

    // my workouts: show workouts and workout menu
    public static String[] mainMenuUser = {
            "My Workouts",
            "My friends",
            "My account",
    };

    public static String[] friendsMenu = {
            "Add a friend",
            "Delete a friend",
            "See Requests"
    };

    public static String[] workoutMenu = {
            "Create Workout",
            "Update Workout",
            "Delete Workout",
    };

    public static String[] accountMenu = {
            "Change Login",
            "Change Email",
            "Change Birthdate",
            "Change Password",
            "Exit from the account"
    };


}
