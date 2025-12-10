package com.blueprint.managers;

import com.blueprint.user.UserManager;
import com.blueprint.workout.ExerciseManager;
import com.blueprint.workout.WorkoutManager;
import lombok.Getter;

@Getter
public class Managers {
    Manager userManager = new UserManager();
    UIManager UIManager = new UIManager();
    DbManager dbManager = new DbManager();
    Manager exerciseManager = new ExerciseManager();
    Manager workoutManager = new WorkoutManager();



}
