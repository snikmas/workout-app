package com.blueprint.workout;

import com.blueprint.managers.Manager;
import com.blueprint.managers.Managers;

public class ExerciseManager implements Manager {

    Managers managers;
    public ExerciseManager(Managers managers){
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
