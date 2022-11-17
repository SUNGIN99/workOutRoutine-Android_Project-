package com.example.workoutroutine.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Routine")
public class NewRoutine_Obj implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "routineTitle")
    private String routineTitle;

    @ColumnInfo(name = "routineDate")
    private String routineDate;

    @Override
    public String toString(){
        return "NewRoutineObj{" +
                "routienID='" + this.id + '\'' +
                ", routineTitle='" + this.routineTitle + '\'' +
                ", routineDate='" + this.routineDate + '\'' +
                '}';
    }

    public NewRoutine_Obj(String routineTitle, String routineDate) {
        this.routineTitle = routineTitle;
        this.routineDate = routineDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoutineTitle() {
        return routineTitle;
    }

    public String getRoutineDate() {
        return routineDate;
    }


    public void setRoutineTitle(String routineTitle) {
        this.routineTitle = routineTitle;
    }

    public void setRoutineDate(String routineDate) {
        this.routineDate = routineDate;
    }

}
