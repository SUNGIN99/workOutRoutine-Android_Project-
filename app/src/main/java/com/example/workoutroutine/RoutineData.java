package com.example.workoutroutine;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Routine")
public class RoutineData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String routinetitle;

    //@ColumnInfo(name = "newRoutine")
    @Embedded private NewRoutine_Obj newroutine;

    @ColumnInfo(name = "date")
    private String createdate;

    public int getId() {
        return id;
    }

    public String getRoutinetitle() {
        return routinetitle;
    }

    public NewRoutine_Obj getNewroutine() {
        return newroutine;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoutinetitle(String routinetitle) {
        this.routinetitle = routinetitle;
    }

    public void setNewroutine(NewRoutine_Obj newroutine) {
        this.newroutine = newroutine;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
