package com.example.workoutroutine.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutroutine.R;
import com.example.workoutroutine.database.RoutineDB;
import com.example.workoutroutine.model.NewRoutine_Obj;
import com.example.workoutroutine.model.RoutineInfo_ItemLists;
import com.example.workoutroutine.model.WorkoutItem_Obj;
import com.example.workoutroutine.recyclerviewadapter.OldRoutine_Adapter_list;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class OldRoutine_Activity_list extends AppCompatActivity {
    // DB에서 할당할 루틴객체
    private TextView title;
    private NewRoutine_Obj selectedRoutine;
    private ArrayList<WorkoutItem_Obj> selectedWorkoutList;
    private int selectId;
    private RoutineInfo_ItemLists oldRoutine;
    RoutineDB routineDB;

    // 리싸이클러뷰 자원
    RecyclerView routineRecycler;
    OldRoutine_Adapter_list oldRoutineAdapter;
    LinearLayoutManager listLayoutManager_oldRoutine;

    // 타이머 자원
    LinearLayout timeCountSettingLV, timeCountLV;
    EditText hourET, minuteET, secondET;
    TextView hourTV, minuteTV, secondTV, finishTV;
    Button startBtn;
    int hour, minute, second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldroutine_selected_recycler);

        // 구성요소
        title = findViewById(R.id.routineTitle2_old);

        // DB
        routineDB = RoutineDB.getInstance(getApplicationContext());

        // 리싸이클러뷰
        routineRecycler = findViewById(R.id.oldRoutineRecycler);
        listLayoutManager_oldRoutine  = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        routineRecycler.setLayoutManager(listLayoutManager_oldRoutine);


        // DB에 저장된 루틴 객체 받아오기
        Intent recv = getIntent();
        selectId = recv.getIntExtra("id", 0);
        Log.d("intent ID:", String.valueOf(selectId));

        oldRoutine = routineDB.routineInfoDao().getRoutineInfo(selectId);
        Log.d("selected routine", oldRoutine.toString());

        selectedRoutine = oldRoutine.getNewRoutineObj();
        selectedWorkoutList = (ArrayList<WorkoutItem_Obj>) oldRoutine.getRoutineItemLists();

        oldRoutineAdapter = new OldRoutine_Adapter_list(getApplicationContext(), selectedWorkoutList, routineDB);
        routineRecycler.setAdapter(oldRoutineAdapter);

        title.setText(selectedRoutine.getRoutineTitle());

        // 타이머 초기화
        timeCountSettingLV = (LinearLayout)findViewById(R.id.timeCountSettingLV);
        timeCountLV = (LinearLayout)findViewById(R.id.timeCountLV);

        hourET = (EditText)findViewById(R.id.hourET);
        minuteET = (EditText)findViewById(R.id.minuteET);
        secondET = (EditText)findViewById(R.id.secondET);

        hourTV = (TextView)findViewById(R.id.hourTV);
        minuteTV = (TextView)findViewById(R.id.minuteTV);
        secondTV = (TextView)findViewById(R.id.secondTV);
        finishTV = (TextView)findViewById(R.id.finishTV);

        startBtn = (Button)findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timeCountSettingLV.setVisibility(View.GONE);
                timeCountLV.setVisibility(View.VISIBLE);

                hourTV.setText(hourET.getText().toString());
                minuteTV.setText(minuteET.getText().toString());
                secondTV.setText(secondET.getText().toString());

                hour = Integer.parseInt(hourET.getText().toString());
                minute = Integer.parseInt(minuteET.getText().toString());
                second = Integer.parseInt(secondET.getText().toString());

                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        // 반복실행할 구문

                        // 0초 이상이면
                        if(second != 0) {
                            //1초씩 감소
                            second--;

                            // 0분 이상이면
                        } else if(minute != 0) {
                            // 1분 = 60초
                            second = 60;
                            second--;
                            minute--;

                            // 0시간 이상이면
                        } else if(hour != 0) {
                            // 1시간 = 60분
                            second = 60;
                            minute = 60;
                            second--;
                            minute--;
                            hour--;
                        }

                        //시, 분, 초가 10이하(한자리수) 라면
                        // 숫자 앞에 0을 붙인다 ( 8 -> 08 )
                        if(second <= 9){
                            secondTV.setText("0" + second);
                        } else {
                            secondTV.setText(Integer.toString(second));
                        }

                        if(minute <= 9){
                            minuteTV.setText("0" + minute);
                        } else {
                            minuteTV.setText(Integer.toString(minute));
                        }

                        if(hour <= 9){
                            hourTV.setText("0" + hour);
                        } else {
                            hourTV.setText(Integer.toString(hour));
                        }

                        // 시분초가 다 0이라면 toast를 띄우고 타이머를 종료한다..
                        if(hour == 0 && minute == 0 && second == 0) {
                            timer.cancel();//타이머 종료
                            finishTV.setText("운동시간이 완료되었습니다!.");
                        }
                    }
                };

                //타이머를 실행
                timer.schedule(timerTask, 0, 1000); //Timer 실행
            }
        });
    }
}
