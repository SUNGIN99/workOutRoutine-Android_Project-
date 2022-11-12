package com.example.workoutroutine;

import java.io.Serializable;
import java.util.ArrayList;

public class NewRoutine_Obj implements Serializable {

    private String routineName;
    private String routinedate;
    private ArrayList<NewRoutineItem> selected;

    public NewRoutine_Obj(String routineName, String routinedate, ArrayList<NewRoutineItem> selected) {
        this.routineName = routineName;
        this.routinedate = routinedate;
        this.selected = selected;
    }

    public String getRoutineName() {
        return routineName;
    }

    public String getRoutinedate() {
        return routinedate;
    }

    public ArrayList<NewRoutineItem> getSelected() {
        return selected;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public void setRoutinedate(String routinedate) {
        this.routinedate = routinedate;
    }

    public void setSelected(ArrayList<NewRoutineItem> selected) {
        this.selected = selected;
    }
}
