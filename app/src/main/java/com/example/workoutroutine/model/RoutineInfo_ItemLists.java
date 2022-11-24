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

    @Override
    public String toString() {
        return "InsertedRoutineObj{" +
                "routineId = '" + this.newRoutineObj.getId() + '\''+
                ", routineTitle = '" + this.newRoutineObj.getRoutineTitle() +'\''+
                ", routineDate = '" + this.newRoutineObj.getRoutineDate() + '\''+
                ", workoutLists = '" + String.valueOf(this.routineItemLists) + '}';
    }

    public NewRoutine_Obj getNewRoutineObj() {
        return newRoutineObj;
    }

    public List<WorkoutItem_Obj> getRoutineItemLists() {
        return routineItemLists;
    }

    public void setNewRoutineObj(NewRoutine_Obj newRoutineObj) {
        this.newRoutineObj = newRoutineObj;
    }

    public void setRoutineItemLists(List<WorkoutItem_Obj> routineItemLists) {
        this.routineItemLists = routineItemLists;
    }
}
