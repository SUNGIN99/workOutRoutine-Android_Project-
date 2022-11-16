package com.example.workoutroutine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewRoutine_Activity_list extends AppCompatActivity {
    private Button routineButton, makeButton; // newRoutine 모으기
    private EditText routineEditText;
    private String routinedate, routineTitle;

    // <22.11.12> 화면에 띄울 루틴 목록(RoutineList 인텐트에서 고른 메뉴들을 전부다 저장함 -> 어뎁터로 사용해서 바인드 시켜서 루틴생성할 예정)
    // <22.11.12-2 > 해당운동의 횟수와 세트 수를 그대로 담아서 이동전환하기 위해서 newRoutineItem 객체로 ArrayList를 만듬
    private ArrayList<NewRoutineItem> selected = null;

    // <22.11.12-2>
    private NewRoutine_Obj newNewRoutineObj;

    // <22.11.12-2> 새로운 루틴 운동목록 리사이클러뷰
    RecyclerView routineRecycler;
    NewRoutine_Adapter_list newRoutineAdapter;
    LinearLayoutManager listLayoutManager_newRoutine;
    // adapter에 바인드할 리스트 = selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newroutine_selected_recycler);

        // <22.11.12 - 2> 선택한 운동을 리스트 리싸이클러뷰로
        routineRecycler = findViewById(R.id.newRoutineRecycler);
        listLayoutManager_newRoutine = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        
        // <22.11.12-2> 루틴 타이틀 에디트 텍스트
        routineEditText = (EditText)findViewById(R.id.routineTitle2);
        
        // <22.11.12-2> 루틴 생성 완료 버튼
        makeButton = (Button)findViewById(R.id.complete);
        makeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                routineTitle = String.valueOf(routineEditText.getText().toString());

                if (!routineTitle.equals("")){
                    routinedate = newRoutineDate();

                    // <22.11.12-2> 새 루틴 객체를 생성(루틴 제목, 날짜, 운동목록)
                    newNewRoutineObj = new NewRoutine_Obj(routineTitle, routinedate, selected);

                    Log.d("Title" , routineTitle);
                    for (NewRoutineItem o : selected){
                        Log.d(Integer.toString(o.getNumber()),
                                "[" + o.getName() + ": " + o.getReps() + " reps " + o.getSets() + " sets ]" );
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
                startActivityForResult(newRoutine, 100); // 운동 선택 리퀘스트코드 = 100
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            if(resultCode == Activity.RESULT_OK){
                selected = (ArrayList<NewRoutineItem>) data.getSerializableExtra("selected"); // <22.11.12> 고른 루틴 결과를 리스트로 반환 받아서 저장
                for (NewRoutineItem s : selected){
                    Log.d("selected_routine:", s.getName());
                }

                if(selected != null){
                    newRoutineAdapter = new NewRoutine_Adapter_list(getApplicationContext(), selected);
                    routineRecycler.setLayoutManager(listLayoutManager_newRoutine);
                    routineRecycler.setAdapter(newRoutineAdapter);
                }
            }
        }
    }

    String newRoutineDate(){
        SimpleDateFormat formatType = new SimpleDateFormat("yyyy-mm-dd");
        return formatType.format(new Date());
    }
}
