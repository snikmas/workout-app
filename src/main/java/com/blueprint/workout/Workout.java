package com.blueprint.workout;

import com.blueprint.constants.Category;
import com.blueprint.constants.MuscleGroup;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Workout {
    private String title;
    private String description;
    private List<Category> categories;
    private List<MuscleGroup> muscleGroups;
    private int duration;

}
