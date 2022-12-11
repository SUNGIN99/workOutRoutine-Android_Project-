package com.example.workoutroutine.recyclerviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutroutine.R;
import com.example.workoutroutine.database.RoutineDB;
import com.example.workoutroutine.model.WorkoutItem_Obj;

import java.util.ArrayList;

public class OldRoutine_Adapter_list extends RecyclerView.Adapter<OldRoutine_Adapter_list.oldRoutine_ViewHolder>{

    Context context;
    ArrayList<WorkoutItem_Obj> selected;

    RoutineDB routineDB;

    public OldRoutine_Adapter_list(Context context, ArrayList<WorkoutItem_Obj> selected, RoutineDB routineDB) {
        super();
        this.context = context;
        this.selected = selected;
        this.routineDB = routineDB;
    }



    @Override
    public void onBindViewHolder(@NonNull oldRoutine_ViewHolder holder, int position) {
        holder.name.setText(selected.get(position).getWorkoutName());
        holder.reps.setText(String.valueOf(selected.get(position).getReps()));
        holder.sets.setText(String.valueOf(selected.get(position).getSets()));
    }

    @NonNull
    @Override
    public oldRoutine_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newroutine_selected_linear_item, parent, false);
        return new OldRoutine_Adapter_list.oldRoutine_ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return selected.size();
    }

    public class oldRoutine_ViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView name;
        EditText reps;
        EditText sets;

        public oldRoutine_ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.ID2);
            name = itemView.findViewById(R.id.listworkoutname);
            reps = itemView.findViewById(R.id.Reps);
            sets = itemView.findViewById(R.id.Sets);
        }
    }
}
