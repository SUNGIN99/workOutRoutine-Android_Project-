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

    @Query("SELECT * FROM Routine WHERE id = :id")
    NewRoutine_Obj getRoutineById(int id);

    @Query("DELETE FROM Routine WHERE routineTitle = :name")
    void deleteNewRoutineObj(String name);

    @Query("DELETE FROM Routine WHERE id = :id")
    void deleteNewRoutineObj(int id);

    @Query("SELECT * FROM ROUTINE ORDER BY id DESC LIMIT 1")
    NewRoutine_Obj getLatestRoutine();


    @Query("UPDATE ROUTINE SET routineTitle = :routineTitle WHERE id = :id")
    void updatedRoutineTitle(String routineTitle, int id);

    @Query("UPDATE ROUTINE SET routineDate = :routineDate WHERE id = :id")
    void updatedRoutineDate(String routineDate, int id);
}
