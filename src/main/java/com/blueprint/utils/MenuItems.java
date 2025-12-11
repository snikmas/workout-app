package com.blueprint.utils;

public class MenuItems {
    // if user == null -> write signup/sign in;
    //otherwise -> check your own
    public static String[] mainMenuBasicItems = {
            "My Workouts",
            "My Account",
            "My Friends",
            "Top Workouts",
            "All Exercises",
            "All Workouts",
            "Exit"
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
