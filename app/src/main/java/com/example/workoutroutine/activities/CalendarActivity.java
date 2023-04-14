package com.example.workoutroutine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutroutine.R;
import com.example.workoutroutine.database.RoutineDB;
import com.example.workoutroutine.model.RoutineInfo_ItemLists;
import com.example.workoutroutine.recyclerviewadapter.Routine_Adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {
    public CalendarView calendarView;
    public ImageButton backButton;

    RecyclerView routineRecycler;
    Routine_Adapter routineAdapter;
    RoutineDB routineDB;

    ArrayList<RoutineInfo_ItemLists> RoutineInfobyDate;

    Calendar selectCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        routineDB = RoutineDB.getInstance(getApplicationContext());
        routineRecycler = findViewById(R.id.day_routine);
        routineRecycler.setLayoutManager(new LinearLayoutManager(this));
        RoutineInfobyDate = (ArrayList<RoutineInfo_ItemLists>) routineDB.routineInfoDao().getRoutineInfoListbyDate(newRoutineDate());

        routineAdapter = new Routine_Adapter(getApplicationContext(), RoutineInfobyDate, routineDB, newRoutineDate());
        routineRecycler.setAdapter(routineAdapter);

        // 달력 클릭
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                Log.d("Calendar", "year : " + year + ", month : "+ (month+1) +", day : "+day);

                SimpleDateFormat formatType = new SimpleDateFormat("yyyy-MM-dd");
                selectCalendar = Calendar.getInstance();
                selectCalendar.set(Calendar.YEAR, year);
                selectCalendar.set(Calendar.MONTH, month);
                selectCalendar.set(Calendar.DAY_OF_MONTH, day);

                String selectedDate = formatType.format(selectCalendar.getTime());
                Log.d("CalendarSelected", selectedDate);

                RoutineInfobyDate = (ArrayList<RoutineInfo_ItemLists>) routineDB.routineInfoDao().getRoutineInfoListbyDate(selectedDate);
                routineAdapter = new Routine_Adapter(getApplicationContext(), RoutineInfobyDate, routineDB, selectedDate);
                routineAdapter.setOnItemClickListener(new Routine_Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int id, int pos) {
                        Log.d("selected Pos:", String.valueOf(id));
                        Intent intent = new Intent(getApplicationContext(), OldRoutine_Activity_list.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });

                routineRecycler.setAdapter(routineAdapter);

                routineAdapter.notifyDataSetChanged();
            }
        });

        //backButton(); //뒤로 가기 버튼 리스너

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

    /*private void backButton() {
        backButton = (ImageButton) findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                finish();
            }
        });
    }*/

    private String newRoutineDate(){
        SimpleDateFormat formatType = new SimpleDateFormat("yyyy-MM-dd");
        return formatType.format(new Date());
    }
}