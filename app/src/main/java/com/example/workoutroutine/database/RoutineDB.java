package com.example.workoutroutine.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.workoutroutine.dao.NewRoutine_DAO;
import com.example.workoutroutine.dao.RoutineInfo_DAO;
import com.example.workoutroutine.dao.WorkoutItem_DAO;
import com.example.workoutroutine.model.NewRoutine_Obj;
import com.example.workoutroutine.model.RoutineInfo_ItemLists;
import com.example.workoutroutine.model.WorkoutItem_Obj;

@Database(entities={NewRoutine_Obj.class, WorkoutItem_Obj.class}, version = 1, exportSchema = false)
public abstract class RoutineDB extends RoomDatabase {
    private static RoutineDB routineDB;

    public synchronized static RoutineDB getInstance(Context context){
        if(routineDB == null) {
            routineDB = Room.databaseBuilder(context.getApplicationContext(), RoutineDB.class, "routineDB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return routineDB;
    }

    public abstract NewRoutine_DAO newRoutineDao();
    public abstract WorkoutItem_DAO workoutItemDao();
    public abstract RoutineInfo_DAO routineInfoDao();

}
