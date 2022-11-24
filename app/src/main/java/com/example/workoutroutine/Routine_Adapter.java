package com.example.workoutroutine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutroutine.database.RoutineDB;
import com.example.workoutroutine.model.NewRoutine_Obj;
import com.example.workoutroutine.model.RoutineInfo_ItemLists;

import java.util.ArrayList;

public class Routine_Adapter extends RecyclerView.Adapter<Routine_Adapter.ViewHolder> {

    private Context context;

    private ArrayList<RoutineInfo_ItemLists> insertedAllRoutineInfo;

    RoutineDB routineDB;

    public Routine_Adapter(Context context, ArrayList<RoutineInfo_ItemLists> insertedAllRoutineInfo, RoutineDB routineDB) {
        this.context = context;
        this.insertedAllRoutineInfo = insertedAllRoutineInfo;
        this.routineDB = routineDB;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.routine_info_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RoutineInfo_ItemLists data = insertedAllRoutineInfo.get(position);

        holder.title.setText(data.getNewRoutineObj().getRoutineTitle());
        holder.date.setText(data.getNewRoutineObj().getRoutineDate());

        holder.btDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //dataList.remove(holder.getAdapterPosition());


                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return insertedAllRoutineInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id, title, date;
        ImageView btEdit, btDelete;

        public ViewHolder(View itemView){
            super(itemView);
            id = itemView.findViewById(R.id.ID1);
            title = itemView.findViewById(R.id.routineTitle1);
            date = itemView.findViewById(R.id.routineDate);
            btEdit = itemView.findViewById(R.id.bt_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);
        }
    }
}
