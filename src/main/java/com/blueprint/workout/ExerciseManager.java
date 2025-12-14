package com.blueprint.workout;

import com.blueprint.managers.Managers;
import com.blueprint.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseManager {
    Logger log = LoggerFactory.getLogger(ExerciseManager.class.getSimpleName());

    Managers managers;
    public ExerciseManager(Managers managers){
        this.managers = managers;
    }

    public Exercise create(Exercise data){
        return null;
    }
    public Exercise update(Exercise data){
        return null;
    }
    public void delete (Exercise data){
        return;
    }

    public List<Exercise> getAllExercises(){
        ResultSet res = managers.getDbManager().getAllExercises();

        List<Exercise> exerciseList = new ArrayList<>();
        try {
            while(res.next()){
                exerciseList.add(Utilities.mapExercise(res));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(!exerciseList.isEmpty()) return exerciseList;
        return null;
    }
}
