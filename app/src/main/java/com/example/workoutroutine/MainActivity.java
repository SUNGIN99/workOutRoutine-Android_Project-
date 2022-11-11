package com.example.workoutroutine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{
    private Button newRoutine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newRoutine = (Button)findViewById(R.id.addRoutine);
        newRoutine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent newRoutine = new Intent(getApplicationContext(), RoutineList.class);
                startActivity(newRoutine);
            }
        });
    }
}