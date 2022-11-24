package com.example.workoutroutine.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.workoutroutine.model.NewRoutine_Obj;
import com.example.workoutroutine.model.WorkoutItem_Obj;

import java.util.List;

@Dao
public interface WorkoutItem_DAO {
    @Insert(onConflict = REPLACE)
    void insert(WorkoutItem_Obj workoutObj);

    @Delete
    void delete(WorkoutItem_Obj workoutObj);

    @Query("SELECT * FROM workOutInfo ORDER BY number ASC")
    List<WorkoutItem_Obj> getRoutineWorkoutList ();

    @Query("SELECT * FROM workOutInfo WHERE workoutName = :name")
    WorkoutItem_Obj getWorkOutByName(String name);

    @Query("DELETE FROM workOutInfo WHERE workoutName = :name")
    void deleteWorkOutByName(String name);

    @Query("SELECT * FROM workOutInfo WHERE routineIdx = :parentid")
    List<WorkoutItem_Obj> getRoutineWorkoutListBy_routineIdx(int parentid);

    @Query("UPDATE workOutInfo SET reps = :reps WHERE number = :number")
    void updateReps(int number, int reps);

    @Query("UPDATE workOutInfo SET sets = :sets  WHERE number = :number")
    void updateSets(int number, int sets);

}
