package com.example.workoutroutine.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workoutroutine.recyclerviewadapter.NewRoutine_Adapter_list;
import com.example.workoutroutine.R;
import com.example.workoutroutine.database.RoutineDB;
import com.example.workoutroutine.model.WorkoutItem_Obj;
import com.example.workoutroutine.model.NewRoutine_Obj;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewRoutine_Activity_list extends AppCompatActivity {
    private Button routineButton, makeButton, datePopup; // newRoutine 모으기
    private EditText routineEditText;
    private String routinedate, routineTitle;
    private TextView setRoutineDate;
    // <22.11.18>
    private RoutineDB routineDB;
    int parentId;

    // <22.11.12> 화면에 띄울 루틴 목록(RoutineList 인텐트에서 고른 메뉴들을 전부다 저장함 -> 어뎁터로 사용해서 바인드 시켜서 루틴생성할 예정)
    // <22.11.12-2 > 해당운동의 횟수와 세트 수를 그대로 담아서 이동전환하기 위해서 newRoutineItem 객체로 ArrayList를 만듬
    private ArrayList<WorkoutItem_Obj> selected;
    private ArrayList<WorkoutItem_Obj> insertedWorkoutItemList;

    // <22.11.12-2>
    public NewRoutine_Obj newNewRoutineObj;

    // <22.11.12-2> 새로운 루틴 운동목록 리사이클러뷰
    RecyclerView routineRecycler;
    NewRoutine_Adapter_list newRoutineAdapter;
    LinearLayoutManager listLayoutManager_newRoutine;
    // adapter에 바인드할 리스트 = selected;


    // <22.12.11> 데이트 Picker 다이얼로그
    Calendar myCalendar;
    DatePickerDialog popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newroutine_selected_recycler);

        //<22.11.18>
        routineDB = RoutineDB.getInstance(getApplicationContext());

        // <22.11.12 - 2> 선택한 운동을 리스트 리싸이클러뷰로
        routineRecycler = findViewById(R.id.newRoutineRecycler);
        listLayoutManager_newRoutine = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        // <22.11.12-3> 새 루틴 객체를 생성(루틴 제목, 날짜, 운동목록)
        newNewRoutineObj = new NewRoutine_Obj(null, newRoutineDate());
        routineDB.newRoutineDao().insert(newNewRoutineObj);

        //<22.11.18>
        parentId = routineDB.newRoutineDao().getLatestRoutine().getId();
        Log.d("nOBJList: ", Integer.toString(parentId));
        selected = (ArrayList<WorkoutItem_Obj>) routineDB.workoutItemDao().getRoutineWorkoutListBy_routineIdx(parentId);

        //<22.11.18> 여기부터할것
        newRoutineAdapter = new NewRoutine_Adapter_list(getApplicationContext(), selected, routineDB);
        routineRecycler.setLayoutManager(listLayoutManager_newRoutine);
        routineRecycler.setAdapter(newRoutineAdapter);

        // <22.12.11> 날짜선택 컴포넌트
        datePopup = (Button)findViewById(R.id.DateDialog);
        setRoutineDate = (TextView) findViewById(R.id.setRoutineDate);

        // <22.11.12-2> 루틴 타이틀 에디트 텍스트
        routineEditText = (EditText)findViewById(R.id.routineTitle2);

        // <22.11.12-2> 루틴 생성 완료 버튼
        makeButton = (Button)findViewById(R.id.complete);
        makeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                routineTitle = String.valueOf(routineEditText.getText().toString());

                Log.d("whatthefuck:", String.valueOf(insertedWorkoutItemList));
                if (!routineTitle.equals("")){
                    newNewRoutineObj.setRoutineTitle(routineTitle);
                    routineDB.newRoutineDao().updatedRoutineTitle(routineTitle, parentId);
                    Log.d("Title" , routineTitle);

                    for (WorkoutItem_Obj o : insertedWorkoutItemList){
                        Log.d(Integer.toString(o.getNumber()),
                                "[" + o.getWorkoutName() + ": " + o.getReps() + " reps " + o.getSets() + " sets ]" );
                    }

                    Intent returnNewRoutine = new Intent();
                    returnNewRoutine.putExtra("newRoutine", newNewRoutineObj);

                    setResult(Activity.RESULT_OK, returnNewRoutine);

                    // 생성 루틴 결과반환
                    Toast.makeText(getApplicationContext(), "루틴 생성완료", Toast.LENGTH_SHORT);
                    finish();
                }
            }
        });

        // <22.11.12> 루틴에 담을 운동 고르기 화면전환 버튼
        routineButton = (Button)findViewById(R.id.newButton);
        routineButton.setOnClickListener(new View.OnClickListener(){ // <22.11.12> 루틴 생성 인텐트 및 startActivityForResult로 고른 루틴 결과 반환 받기
            @Override
            public void onClick(View v){

                Intent newRoutine = new Intent(getApplicationContext(), SelectWorkout_Activity_grid.class);
                //<22.11.18>
                newRoutine.putExtra("routineIdx", routineDB.newRoutineDao().getLatestRoutine().getId());
                startActivityForResult(newRoutine, 100); // 운동 선택 리퀘스트코드 = 100
            }
        });
    }

    @Override
    protected void onStart() {
        // <22.12.11> 루틴 날짜 수정
        super.onStart();
        myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();


                routineDB.newRoutineDao().updatedRoutineDate((String) setRoutineDate.getText(), parentId);
            }
        };

        datePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("selected Date", "fuck");
                new DatePickerDialog(NewRoutine_Activity_list.this, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            if(resultCode == Activity.RESULT_OK){
                selected = (ArrayList<WorkoutItem_Obj>) data.getSerializableExtra("selected"); // <22.11.12> 고른 루틴 결과를 리스트로 반환 받아서 저장
                Log.d("(NewRoutine_Activity.java) selected_routine:", String.valueOf(selected));

                //<22.11.18>
                insertedWorkoutItemList = (ArrayList<WorkoutItem_Obj>) routineDB.workoutItemDao().getRoutineWorkoutListBy_routineIdx(parentId);
                for(WorkoutItem_Obj w: insertedWorkoutItemList){
                    Log.d("fuck: ", parentId + ")" + Integer.toString(w.getNumber()));
                }

                //selected = insertedWorkoutItemList;

                if(selected != null){
                    newRoutineAdapter = new NewRoutine_Adapter_list(getApplicationContext(), insertedWorkoutItemList, routineDB);
                    routineRecycler.setLayoutManager(listLayoutManager_newRoutine);
                    routineRecycler.setAdapter(newRoutineAdapter);

                    newRoutineAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    String newRoutineDate(){
        SimpleDateFormat formatType = new SimpleDateFormat("yyyy-MM-dd");
        return formatType.format(new Date());
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        TextView et_date = (TextView) findViewById(R.id.setRoutineDate);
        et_date.setText(sdf.format(myCalendar.getTime()));
    }
}
