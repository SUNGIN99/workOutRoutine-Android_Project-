package com.example.workoutroutine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private Button routineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        routineButton = (Button)findViewById(R.id.button);
        routineButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent newRoutine = new Intent(getApplicationContext(), RoutineList.class);
                startActivity(newRoutine);
            }
        });
    }



}