package com.example.workoutroutine;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {RoutineData.class}, version = 1, exportSchema = false)
//@TypeConverters({RoutineDataConverters.class, NewRoutineItemListConverters.class})
public abstract class RoutineRoomDB extends RoomDatabase {

    private static RoutineRoomDB routinedb;
    private static String DATABASE_NAME = "routinedb";

    public synchronized static RoutineRoomDB getInstance(Context context){
        if (routinedb == null){
            routinedb = Room.databaseBuilder(context.getApplicationContext(), RoutineRoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return routinedb;
    }

    public abstract RoutineDao routineDao();
}
