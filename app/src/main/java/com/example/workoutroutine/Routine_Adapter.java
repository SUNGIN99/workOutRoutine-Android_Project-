package com.example.workoutroutine;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Routine_Adapter extends RecyclerView.Adapter<Routine_Adapter.ViewHolder> {

    private List<RoutineData> dataList;
    private Context context;
    private RoutineRoomDB routinedb;

    public Routine_Adapter(Context context, List<RoutineData> dataList) {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.routine_info_item, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RoutineData data = dataList.get(position);
        routinedb = RoutineRoomDB.getInstance(context);

        NewRoutine_Obj routineitem = data.getNewroutine();

        holder.title.setText(routineitem.getRoutineName());
        holder.date.setText(routineitem.getRoutinedate());

        /*holder.btEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });*/

        holder.btDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                RoutineData routineData = dataList.get(holder.getAdapterPosition());
                routinedb.routineDao().delete(routineData);

                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, dataList.size());
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
