package com.example.workoutroutine;

import androidx.room.ColumnInfo;

import java.io.Serializable;

public class NewRoutineItem implements Serializable {
    private String name;
    private int number;
    private int reps;
    private int sets;

    public NewRoutineItem(String name, int reps, int sets) {
        this.name = name;
        this.reps = reps;
        this.sets = sets;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
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
}
