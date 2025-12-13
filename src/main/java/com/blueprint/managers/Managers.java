package com.blueprint.managers;

import com.blueprint.user.UserManager;
import com.blueprint.workout.ExerciseManager;
import com.blueprint.workout.WorkoutManager;
import lombok.Getter;

@Getter
public class Managers {
    UserManager userManager;
    UIManager UIManager;
    DbManager dbManager;
    ExerciseManager exerciseManager;
    WorkoutManager workoutManager;

    public Managers(){
        userManager = new UserManager(this);
        UIManager = new UIManager(this);
        dbManager = new DbManager(this);
        exerciseManager = new ExerciseManager(this);
        workoutManager = new WorkoutManager(this);
    }


}
