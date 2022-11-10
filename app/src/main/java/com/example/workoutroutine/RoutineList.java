package com.example.workoutroutine;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RoutineList extends AppCompatActivity {

    RecyclerView recyclerView;
    ListAdapterGrid adapter;
    GridLayoutManager layoutManager;

    ArrayList<workoutItem> list = new ArrayList<workoutItem>(){{
        // 1) 복근
        add(new workoutItem("복근", R.drawable.a0_situp));

        // 2) 가슴
        add(new workoutItem("벤치프레스", R.drawable.c0_benchpress)); // 벤치프레스
        add(new workoutItem("I_벤치프레스", R.drawable.c0_inclinebp));
        add(new workoutItem("D_벤치프레스", R.drawable.c0_declinebp));
        add(new workoutItem("덤벨프레스", R.drawable.c1_dumbellpress)); // 덤벨프레스
        add(new workoutItem("I_덤벨프레스", R.drawable.c1_inclinedp));
        add(new workoutItem("덤벨플라이", R.drawable.c2_dumbellfly)); // 플라이
        add(new workoutItem("I덤벨플라이", R.drawable.c2_inclinedf));
        add(new workoutItem("케이블플라이", R.drawable.c3_cablefly));
        add(new workoutItem("머신플라이", R.drawable.c3_machinefly));
        add(new workoutItem("체스트프레스", R.drawable.c4_chestpress)); // 머신
        add(new workoutItem("스미스머신프레스", R.drawable.c5_smithbf));
        add(new workoutItem("푸쉬업", R.drawable.c6_pushup)); // 맨몸
        add(new workoutItem("딥스", R.drawable.c6_dips));

        // 3) 등
        add(new workoutItem("데드리프트", R.drawable.b0_deadlift));
        add(new workoutItem("스모데드리프트", R.drawable.b0_sumodl));
        add(new workoutItem("벤트오버로우", R.drawable.b0_bentoverrow));
        add(new workoutItem("T바로우", R.drawable.b0_tbarrow));
        add(new workoutItem("벤치풀", R.drawable.b0_benchpull));
        add(new workoutItem("렉풀", R.drawable.b0_rackpull));
        add(new workoutItem("업라이트", R.drawable.b0_uprr));

        // 4) 어깨
        add(new workoutItem("사이드레터럴레이즈", R.drawable.s0_sidelr));
        add(new workoutItem("프론트레이즈", R.drawable.s0_frontraise));
        add(new workoutItem("숄더덤벨프레스", R.drawable.s0_dbpress));
        add(new workoutItem("아놀드프레스", R.drawable.s0_arnolrdpress));
        add(new workoutItem("숄더프레스", R.drawable.s1_shoulderpress));
        add(new workoutItem("밀리터리프레스", R.drawable.s1_mlitraypress));
        add(new workoutItem("바벨프론트", R.drawable.s1_bfrontraise));
        add(new workoutItem("슈러그", R.drawable.s1_barbellshrug));
        add(new workoutItem("통나무프레스", R.drawable.s1_logpress));
        add(new workoutItem("머신숄더프레스", R.drawable.s1_mshouldpress));
        add(new workoutItem("케이블레터럴레이즈", R.drawable.s3_cablelateralr));
        add(new workoutItem("케이플 풀", R.drawable.s3_facepull));
        add(new workoutItem("머신백플라이", R.drawable.s3_machinfly));

        // 5) 하체
        add(new workoutItem("스쿼트", R.drawable.l0_frontsquat));
        add(new workoutItem("F스쿼트", R.drawable.l0_squat));
        add(new workoutItem("고블릿스쿼트", R.drawable.l0_gobletsquat));
        add(new workoutItem("핵스쿼트", R.drawable.l0_hacksquat));
        add(new workoutItem("레그프레스", R.drawable.l1_legpress));
        add(new workoutItem("레그컬", R.drawable.l1_legcurl));
        add(new workoutItem("레그익스텐션", R.drawable.l1_legextension));
        add(new workoutItem("힙쓰러스트", R.drawable.l2_hipttrust));
        add(new workoutItem("런지", R.drawable.l3_lunge));

    }};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine_select_recycler);

        recyclerView = findViewById(R.id.recyclerGridView);
        adapter = new ListAdapterGrid(getApplicationContext(), list);

        layoutManager = new GridLayoutManager(getApplicationContext(), 3);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
