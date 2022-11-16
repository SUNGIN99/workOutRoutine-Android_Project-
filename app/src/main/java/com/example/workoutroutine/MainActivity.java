package com.example.workoutroutine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private Button newRoutine;
    private List<RoutineData> showRoutines = new ArrayList<>();
    private NewRoutine_Obj newRoutineObj;

    RecyclerView routineRecycler;
    RoutineRoomDB routinedb;
    Routine_Adapter routineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        routinedb = RoutineRoomDB.getInstance(this);
        showRoutines = routinedb.routineDao().getAll();

        Log.d("start", String.valueOf(showRoutines));

        routineRecycler = findViewById(R.id.routines);

        routineRecycler.setLayoutManager(new LinearLayoutManager(this));
        routineAdapter = new Routine_Adapter(getApplicationContext(), showRoutines);
        routineRecycler.setAdapter(routineAdapter);

        newRoutine = (Button)findViewById(R.id.addRoutine);
        newRoutine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent newRoutine = new Intent(getApplicationContext(), NewRoutine_Activity_list.class);
                startActivityForResult(newRoutine, 200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200) {
            if(resultCode == AppCompatActivity.RESULT_OK){
                newRoutineObj = (NewRoutine_Obj) data.getSerializableExtra("newRoutine");
                String routineName = newRoutineObj.getRoutineName();
                String routineDate = newRoutineObj.getRoutinedate();

                Log.d("newroutine Title: ", routineName);
                for (NewRoutineItem i : newRoutineObj.getSelected()){
                    Log.d("obj. selected:", i.getName());
                }

                RoutineData routine_data = new RoutineData();
                routine_data.setRoutinetitle(routineName);
                routine_data.setCreatedate(routineDate);
                routine_data.setNewroutine(newRoutineObj);

                routinedb.routineDao().insert(routine_data);
                showRoutines.clear();
                showRoutines.addAll(routinedb.routineDao().getAll());
                //Log.d("fuck", String.valueOf(showRoutines));
                routineAdapter.notifyDataSetChanged();
            }
        }
    }
}