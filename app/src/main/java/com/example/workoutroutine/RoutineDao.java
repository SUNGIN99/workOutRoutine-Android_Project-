package com.example.workoutroutine;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoutineDao {

    @Insert(onConflict = REPLACE)
    void insert(RoutineData routineData);

    @Delete
    void delete(RoutineData routineData);

    @Query("SELECT * FROM Routine")
    List<RoutineData> getAll();
}
