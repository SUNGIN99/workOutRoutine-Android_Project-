package com.example.workoutroutine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutroutine.model.NewRoutine_Obj;

import java.util.ArrayList;

public class Routine_Adapter extends RecyclerView.Adapter<Routine_Adapter.ViewHolder> {

    private ArrayList<NewRoutine_Obj> dataList;
    private Context context;


    public Routine_Adapter(Context context, ArrayList<NewRoutine_Obj> dataList) {
        this.context = context;
        this.dataList = dataList;
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
        final NewRoutine_Obj data = dataList.get(position);

        holder.title.setText(data.getRoutineTitle());
        holder.date.setText(data.getRoutineDate());

        holder.btDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dataList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
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
