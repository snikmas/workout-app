package com.blueprint.workout;

import com.blueprint.managers.Manager;
import com.blueprint.managers.Managers;

public class WorkoutManager {

    Managers managers;
    public WorkoutManager(Managers managers){
        this.managers = managers;
    }

    public Workout create(Workout workout){
        if(managers.getDbManager().createWorkout(workout)){
            return workout;
        }
        return null;
    }
    public Workout update(Workout workout){

        return null;
    }
    public boolean delete (Workout workout)
    {
        return true;
    }
}
