package com.example.workoutroutine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class RoutineList extends AppCompatActivity {
    private Button routineButton; // newRoutine 모으기

    // <22.11.12>  화면에 띄울 루틴 목록(RoutineList 인텐트에서 고른 메뉴들을 전부다 저장함 -> 어뎁터로 사용해서 바인드 시켜서 루틴생성할 예정)
    private ArrayList<String> selected;

    // <22.11.12> 임시변수 확인용
    TextView text1;
    TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newroutine);

        routineButton = (Button)findViewById(R.id.newButton);
        routineButton.setOnClickListener(new View.OnClickListener(){ // <22.11.12> 루틴 생성 인텐트 및 startActivityForResult로 고른 루틴 결과 반환 받기
            @Override
            public void onClick(View v){
                Intent newRoutine = new Intent(getApplicationContext(), RoutineSelect.class);
                startActivityForResult(newRoutine, 100); // 운동 선택 리퀘스트코드 = 100
            }
        });
        
        // 리싸이클러뷰 selected 리스트로 어댑터해서 완성할것
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            if(resultCode == Activity.RESULT_OK){
                selected = data.getStringArrayListExtra("selected"); // <22.11.12> 고른 루틴 결과를 리스트로 반환 받아서 저장
                for (String s : selected){
                    Log.d("selected_routine:", s);
                }

                // <22.11.12>임시로 text1과 text2를 만들어서 값을 할당해줬는데, -> 이부분도 리싸이클러뷰로 다 전환해야함.
                text1 = findViewById(R.id.routine1);
                text2 = findViewById(R.id.routine2);

                text1.setText(selected.get(0));
                text2.setText(selected.get(1));
            }
        }
    }
}
