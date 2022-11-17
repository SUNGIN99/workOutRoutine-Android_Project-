package com.example.workoutroutine.model;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.util.List;

public class RoutineInfo_ItemLists {
    @Embedded public NewRoutine_Obj newRoutineObj;
    @Relation(
            parentColumn = "id",
            entityColumn = "routineIdx"
    )
    public List<WorkoutItem_Obj> routineItemLists;

}
