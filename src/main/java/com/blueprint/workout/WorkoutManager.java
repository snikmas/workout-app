package com.blueprint.workout;

import com.blueprint.managers.Manager;
import com.blueprint.managers.Managers;

public class WorkoutManager {

    Managers managers;
    public WorkoutManager(Managers managers){
        this.managers = managers;
    }

    public <T> T create(T data){
        return null;
    }
    public <T> T update(T data){
        return null;
    }
    public <T> void delete (T data){
        return;
    }
}
