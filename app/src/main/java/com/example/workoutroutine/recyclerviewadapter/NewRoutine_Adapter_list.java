package com.example.workoutroutine.recyclerviewadapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutroutine.R;
import com.example.workoutroutine.database.RoutineDB;
import com.example.workoutroutine.model.WorkoutItem_Obj;

import java.util.ArrayList;

public class NewRoutine_Adapter_list extends RecyclerView.Adapter<NewRoutine_Adapter_list.newRoutine_ViewHolder>{
    Context context;
    ArrayList<WorkoutItem_Obj> selected;

    // <<22.11.18> routineDB 사용
    RoutineDB routineDB;

    public NewRoutine_Adapter_list(Context context, ArrayList<WorkoutItem_Obj> selected, RoutineDB routineDB) {
        super();
        this.context = context;
        this.selected = selected;
        this.routineDB = routineDB;
    }

    @Override
    public void onBindViewHolder(newRoutine_ViewHolder holder, int position){
        // <22.11.12-2> selected안에 들어있는 newRoutineItem들을 (순서, 이름, reps, sets)를 모두 설정해줌
        // EditText의 값이 변경되면 activity에서도 계속 사용해야 하기 때문에 addTextChangedListener를 이용해 텍스트 변화 리스너 이벤트 추가
        holder.id.setText(Integer.toString(position+1));
        Log.d("(NewRoutine_Adapter_list.java) selected workouts: ", selected.get(position).toString());
        //Log.d("workoutId:", Integer.toString(selected.get(position).getNumber()));
        holder.name.setText(selected.get(position).getWorkoutName());

        holder.reps.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int nreps = Integer.parseInt(s.toString());
                selected.get(position).setReps(nreps);
                routineDB.workoutItemDao().updateReps(selected.get(position).getNumber(), nreps);
            }
        });
        holder.sets.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int nsets = Integer.parseInt(s.toString());
                selected.get(position).setSets(nsets);
                routineDB.workoutItemDao().updateSets(selected.get(position).getNumber(), nsets);
            }
        });
    }

    @Override
    public newRoutine_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.newroutine_selected_linear_item, parent, false);
        return new newRoutine_ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return selected.size();
    }

    public class newRoutine_ViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView name;
        EditText reps;
        EditText sets;

        public newRoutine_ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.ID2);
            name = itemView.findViewById(R.id.listworkoutname);
            reps = itemView.findViewById(R.id.Reps);
            sets = itemView.findViewById(R.id.Sets);
        }

    }
}
