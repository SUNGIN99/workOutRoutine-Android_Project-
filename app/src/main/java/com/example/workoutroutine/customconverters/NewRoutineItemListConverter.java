package com.example.workoutroutine.customconverters;

import androidx.room.TypeConverters;

import com.example.workoutroutine.model.WorkoutItem_Obj;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NewRoutineItemListConverter {
    @TypeConverters
    public static String fromNewRoutine_Obj(Object obj){
        ArrayList<WorkoutItem_Obj> nobj = (ArrayList<WorkoutItem_Obj>) obj;
        return new Gson().toJson(nobj);
    }

    @TypeConverters
    public static Object toNewRoutine_Obj(String value){
        Type nObjType = new TypeToken<ArrayList<WorkoutItem_Obj>>() {}.getType();
        return new Gson().fromJson(value, nObjType);
    }
}
