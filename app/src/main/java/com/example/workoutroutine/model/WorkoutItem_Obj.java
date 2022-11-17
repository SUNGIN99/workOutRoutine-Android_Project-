package com.example.workoutroutine.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="workOutInfo",
        foreignKeys = @ForeignKey(
                entity = NewRoutine_Obj.class,
                parentColumns = "id",
                childColumns = "routineIdx",
                onDelete = ForeignKey.CASCADE
        ))
public class WorkoutItem_Obj implements Serializable {

    public WorkoutItem_Obj(String workoutName, int reps, int sets) {
        this.workoutName = workoutName;
        this.reps = reps;
        this.sets = sets;
    }

    @PrimaryKey(autoGenerate = true)
    private int number; // id

    @ColumnInfo(name = "workoutName")
    private String workoutName;

    @ColumnInfo(name = "reps")
    private int reps;

    @ColumnInfo(name = "sets")
    private int sets;

    @ColumnInfo(name = "routineIdx")
    private int routineIdx;

    @Override
    public String toString(){
        return "NewRoutineObj{" +
                "workoutID='" + this.number + '\'' +
                ", workoutName='" + this.workoutName + '\'' +
                ", reps='" + this.reps + '\'' +
                ", sets='" + this.sets + '\'' +
                ", routineIdx='" + this.routineIdx + '\'' +
                '}';
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public int getNumber() {
        return number;
    }

    public int getReps() {
        return reps;
    }

    public int getSets() {
        return sets;
    }

    public int getRoutineIdx() {
        return routineIdx;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setRoutineIdx(int routineIdx) {
        this.routineIdx = routineIdx;
    }
}
