package com.example.workoutroutine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText, subtext1, subtext2;
    Button btAdd, btReset;
    RecyclerView recyclerView;

    List<MainData> dataList = new ArrayList<>();
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        subtext1 = findViewById(R.id.sub_text1);
        subtext2 = findViewById(R.id.sub_text2);

        btAdd = findViewById(R.id.bt_add);
        btReset = findViewById(R.id.bt_reset);
        recyclerView = findViewById(R.id.recycler_view);

        database = RoomDB.getInstance(this);
        dataList = database.mainDao().getAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MainAdapter(MainActivity.this, dataList);
        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String sText = editText.getText().toString().trim();
                String ssubtext1 = subtext1.getText().toString().trim();
                String ssubtext2 = subtext2.getText().toString().trim();

                Log.d("input:", sText+"+"+ssubtext1+"+"+ssubtext2);

                ArrayList<String> stringList = new ArrayList<>();
                stringList.add(ssubtext1);
                stringList.add(ssubtext2);

                Log.d("stringList: ", String.valueOf(stringList));

                if(!sText.equals("")){
                    MainData data = new MainData();
                    data.setText(sText);
                    data.setStringList(stringList);
                    database.mainDao().insert(data);

                    editText.setText("");

                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.mainDao().reset(dataList);

                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });


    }
}