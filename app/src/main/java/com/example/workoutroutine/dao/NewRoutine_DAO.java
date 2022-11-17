package com.example.workoutroutine.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.workoutroutine.model.NewRoutine_Obj;
import java.util.List;

@Dao
public interface NewRoutine_DAO {
    @Insert(onConflict = REPLACE)
    void insert(NewRoutine_Obj newRoutineObj);

    @Delete
    void delete(NewRoutine_Obj newRoutineObj);

    @Query("SELECT * FROM Routine ORDER BY id ASC")
    List<NewRoutine_Obj> getRoutineWorkoutList ();

    @Query("SELECT * FROM Routine WHERE routineTitle = :name")
    NewRoutine_Obj getRoutineByName(String name);

    @Query("DELETE FROM Routine WHERE routineTitle = :name")
    void deleteNewRoutineObj(String name);
}
