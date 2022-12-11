package com.example.workoutroutine.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.workoutroutine.R;
import com.example.workoutroutine.recyclerviewadapter.Routine_Adapter;
import com.example.workoutroutine.database.RoutineDB;
import com.example.workoutroutine.model.NewRoutine_Obj;
import com.example.workoutroutine.model.RoutineInfo_ItemLists;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private Button newRoutine;
    private NewRoutine_Obj newRoutineObj;

    RecyclerView routineRecycler;
    Routine_Adapter routineAdapter;
    RoutineDB routineDB;

    ArrayList<RoutineInfo_ItemLists> insertedAllRoutineInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        routineDB = RoutineDB.getInstance(getApplicationContext());

        insertedAllRoutineInfo = (ArrayList<RoutineInfo_ItemLists>) routineDB.routineInfoDao().getRoutineInfoList();
        for (RoutineInfo_ItemLists i : insertedAllRoutineInfo){
            Log.d("all Selected Routine Info : ", i.toString());
        }


        routineRecycler = (RecyclerView)findViewById(R.id.routines);

        routineRecycler.setLayoutManager(new LinearLayoutManager(this));
        routineAdapter = new Routine_Adapter(getApplicationContext(), insertedAllRoutineInfo, routineDB, null);

        routineRecycler.setAdapter(routineAdapter);

        newRoutine = (Button)findViewById(R.id.addRoutine);
        newRoutine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent newRoutine = new Intent(getApplicationContext(), NewRoutine_Activity_list.class);
                startActivityForResult(newRoutine, 200);
            }
        });

        // <22.12.11> 달력 액티비티 활성화
        ImageButton ib_calendar = (ImageButton) findViewById(R.id.ib_calendar);
        ib_calendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivityForResult(intent, 300);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        routineAdapter.setOnItemClickListener(new Routine_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id, int pos) {
                Log.d("selected Pos:", String.valueOf(id));
                Intent intent = new Intent(getApplicationContext(), OldRoutine_Activity_list.class);
                intent.putExtra("id", id);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                newRoutineObj = routineDB.newRoutineDao().getLatestRoutine();

                String routineName = newRoutineObj.getRoutineTitle();
                String routineDate = newRoutineObj.getRoutineDate();

                Log.d("newroutine Title: ", routineName + " (" + routineDate + ")");

                insertedAllRoutineInfo = (ArrayList<RoutineInfo_ItemLists>) routineDB.routineInfoDao().getRoutineInfoList();

                routineAdapter = new Routine_Adapter(getApplicationContext(), insertedAllRoutineInfo, routineDB, null);
                routineRecycler.setAdapter(routineAdapter);
            } else if (resultCode == AppCompatActivity.RESULT_CANCELED) {
                Log.d("Cancle", "cancel");
                NewRoutine_Obj notCompleteObj = routineDB.newRoutineDao().getLatestRoutine();
                routineDB.newRoutineDao().deleteNewRoutineObj(notCompleteObj.getId());
            }
        }
        else if (requestCode == 300){
            if(resultCode == AppCompatActivity.RESULT_CANCELED){
                insertedAllRoutineInfo = (ArrayList<RoutineInfo_ItemLists>) routineDB.routineInfoDao().getRoutineInfoList();
                routineAdapter = new Routine_Adapter(getApplicationContext(), insertedAllRoutineInfo, routineDB, null);
                routineRecycler.setAdapter(routineAdapter);
            }
        }
    }
}