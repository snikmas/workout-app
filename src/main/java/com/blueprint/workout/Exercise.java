package com.blueprint.workout;

import com.blueprint.constants.Category;
import com.blueprint.constants.Difficulty;
import com.blueprint.constants.MuscleGroup;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exercise {
    private String title;
    private String description;
    private Category[] category;
    private MuscleGroup[] muscleGroup;
    private Difficulty difficulty;
}
