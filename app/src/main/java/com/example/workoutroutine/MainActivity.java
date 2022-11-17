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

import com.example.workoutroutine.model.NewRoutine_Obj;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private Button newRoutine;
    private ArrayList<NewRoutine_Obj> showRoutines = new ArrayList<>();
    private NewRoutine_Obj newRoutineObj;

    RecyclerView routineRecycler;
    Routine_Adapter routineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("start", String.valueOf(showRoutines));

        routineRecycler = (RecyclerView)findViewById(R.id.routines);

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
                String routineName = newRoutineObj.getRoutineTitle();
                String routineDate = newRoutineObj.getRoutineDate();

                Log.d("newroutine Title: ", routineName + " (" + routineDate +")");

                showRoutines.add(newRoutineObj);
                Log.d("showroutines", String.valueOf(showRoutines));

                routineAdapter = new Routine_Adapter(getApplicationContext(), showRoutines);
                routineRecycler.setAdapter(routineAdapter);
            }
        }
    }
}