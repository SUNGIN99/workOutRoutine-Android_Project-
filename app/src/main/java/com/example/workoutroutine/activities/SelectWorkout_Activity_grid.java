package com.example.workoutroutine.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutroutine.R;
import com.example.workoutroutine.recyclerviewadapter.SelectWorkout_Adapter_grid;
import com.example.workoutroutine.recyclerviewadapter.Select_workoutItem;
import com.example.workoutroutine.database.RoutineDB;
import com.example.workoutroutine.model.WorkoutItem_Obj;

import java.util.ArrayList;

public class SelectWorkout_Activity_grid extends AppCompatActivity {

    RecyclerView recyclerView;
    SelectWorkout_Adapter_grid selectAdapter;
    GridLayoutManager gridLayoutManager;

    ArrayList<Select_workoutItem> list = new ArrayList<Select_workoutItem>(){{
        // 1) 복근
        add(new Select_workoutItem("복근", R.drawable.a0_situp));

        // 2) 가슴
        add(new Select_workoutItem("벤치프레스", R.drawable.c0_benchpress)); // 벤치프레스
        add(new Select_workoutItem("I_벤치프레스", R.drawable.c0_inclinebp));
        add(new Select_workoutItem("D_벤치프레스", R.drawable.c0_declinebp));
        add(new Select_workoutItem("덤벨프레스", R.drawable.c1_dumbellpress)); // 덤벨프레스
        add(new Select_workoutItem("I_덤벨프레스", R.drawable.c1_inclinedp));
        add(new Select_workoutItem("덤벨플라이", R.drawable.c2_dumbellfly)); // 플라이
        add(new Select_workoutItem("I덤벨플라이", R.drawable.c2_inclinedf));
        add(new Select_workoutItem("케이블플라이", R.drawable.c3_cablefly));
        add(new Select_workoutItem("머신플라이", R.drawable.c3_machinefly));
        add(new Select_workoutItem("체스트프레스", R.drawable.c4_chestpress)); // 머신
        add(new Select_workoutItem("스미스머신프레스", R.drawable.c5_smithbf));
        add(new Select_workoutItem("푸쉬업", R.drawable.c6_pushup)); // 맨몸
        add(new Select_workoutItem("딥스", R.drawable.c6_dips));

        // 3) 등
        add(new Select_workoutItem("데드리프트", R.drawable.b0_deadlift));
        add(new Select_workoutItem("스모데드리프트", R.drawable.b0_sumodl));
        add(new Select_workoutItem("벤트오버로우", R.drawable.b0_bentoverrow));
        add(new Select_workoutItem("T바로우", R.drawable.b0_tbarrow));
        add(new Select_workoutItem("벤치풀", R.drawable.b0_benchpull));
        add(new Select_workoutItem("렉풀", R.drawable.b0_rackpull));
        add(new Select_workoutItem("업라이트", R.drawable.b0_uprr));

        // 4) 어깨
        add(new Select_workoutItem("사이드레터럴레이즈", R.drawable.s0_sidelr));
        add(new Select_workoutItem("프론트레이즈", R.drawable.s0_frontraise));
        add(new Select_workoutItem("숄더덤벨프레스", R.drawable.s0_dbpress));
        add(new Select_workoutItem("아놀드프레스", R.drawable.s0_arnolrdpress));
        add(new Select_workoutItem("숄더프레스", R.drawable.s1_shoulderpress));
        add(new Select_workoutItem("밀리터리프레스", R.drawable.s1_mlitraypress));
        add(new Select_workoutItem("바벨프론트", R.drawable.s1_bfrontraise));
        add(new Select_workoutItem("슈러그", R.drawable.s1_barbellshrug));
        add(new Select_workoutItem("통나무프레스", R.drawable.s1_logpress));
        add(new Select_workoutItem("머신숄더프레스", R.drawable.s1_mshouldpress));
        add(new Select_workoutItem("케이블레터럴레이즈", R.drawable.s3_cablelateralr));
        add(new Select_workoutItem("케이플 풀", R.drawable.s3_facepull));
        add(new Select_workoutItem("머신백플라이", R.drawable.s3_machinfly));

        // 5) 하체
        add(new Select_workoutItem("스쿼트", R.drawable.l0_frontsquat));
        add(new Select_workoutItem("F스쿼트", R.drawable.l0_squat));
        add(new Select_workoutItem("고블릿스쿼트", R.drawable.l0_gobletsquat));
        add(new Select_workoutItem("핵스쿼트", R.drawable.l0_hacksquat));
        add(new Select_workoutItem("레그프레스", R.drawable.l1_legpress));
        add(new Select_workoutItem("레그컬", R.drawable.l1_legcurl));
        add(new Select_workoutItem("레그익스텐션", R.drawable.l1_legextension));
        add(new Select_workoutItem("힙쓰러스트", R.drawable.l2_hipttrust));
        add(new Select_workoutItem("런지", R.drawable.l3_lunge));

    }};

    // <22.11.12-2 > 해당운동의 횟수와 세트 수를 그대로 담아서 이동전환하기 위해서 newRoutineItem 객체로 ArrayList를 만듬
    ArrayList<WorkoutItem_Obj> selected = new ArrayList<>();
    Button selectComplete;

    // <<22.11.18> RoutineDB 생성 후에 WorkoutItem_Obj 추가;
    RoutineDB routineDB;
    int parentId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_workout_recycler);

        // <22.11.18>
        Intent recv_intent = getIntent();
        parentId = (int) recv_intent.getIntExtra("routineIdx", 0);
        Log.d("Intent Recived routineIdx at SelectWorkout: ", Integer.toString(parentId));

        // <22.11.18> RoutineDB할당
        routineDB = RoutineDB.getInstance(getApplicationContext());

        // <22.11.10> 리싸이클러뷰, 어뎁터 할당
        recyclerView = findViewById(R.id.recyclerGridView);
        selectAdapter = new SelectWorkout_Adapter_grid(getApplicationContext(), list);

        // <22.11.12> 어뎁터에 있는 뷰 홀더에 커스텀 클릭이벤트 설정
        selectAdapter.setOnItemClickListener(new SelectWorkout_Adapter_grid.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                TextView name = (TextView)v;
                WorkoutItem_Obj workoutItem_obj = new WorkoutItem_Obj((String) name.getText(), 0, 0, parentId);
                selected.add(workoutItem_obj);
                routineDB.workoutItemDao().insert(workoutItem_obj);

                Log.d("(SelectWorkout_Activity_grid) selected_routine:", String.valueOf(selected));
                Toast.makeText(getApplicationContext(), name.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        // <22.11.12> activity 루틴 선택 결과 반환
        selectComplete = findViewById(R.id.selectComplete);
        selectComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent routines = new Intent();
                routines.putExtra("selected", selected); // 값 인텐트에 저장후 result로 호출한 액티비티에 반환
                setResult(Activity.RESULT_OK, routines);
                
                Toast.makeText(getApplicationContext(), "루틴 선택완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // <22.11.10>리싸이클러뷰, 어뎁터 할당
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(selectAdapter);

    }


}
