package com.example.workoutroutine.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.workoutroutine.model.RoutineInfo_ItemLists;

import java.util.List;

@Dao
public interface RoutineInfo_DAO {
    @Transaction
    @Query("SELECT * FROM Routine ORDER BY id ASC")
    List<RoutineInfo_ItemLists> getRoutineInfoList();

    @Transaction
    @Query("SELECT * FROM Routine WHERE routineDate = :routineDate")
    List<RoutineInfo_ItemLists> getRoutineInfoListbyDate(String routineDate);

    @Transaction
    @Query("SELECT * FROM Routine WHERE id = :id")
    RoutineInfo_ItemLists getRoutineInfo(int id);



}
