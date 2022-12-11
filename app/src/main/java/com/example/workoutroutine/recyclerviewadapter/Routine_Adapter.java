package com.example.workoutroutine.recyclerviewadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutroutine.R;
import com.example.workoutroutine.database.RoutineDB;
import com.example.workoutroutine.model.RoutineInfo_ItemLists;

import java.util.ArrayList;

public class Routine_Adapter extends RecyclerView.Adapter<Routine_Adapter.ViewHolder> {

    private Context context;

    private ArrayList<RoutineInfo_ItemLists> insertedAllRoutineInfo;

    RoutineDB routineDB;

    String CalendarDate;

    public Routine_Adapter(Context context, ArrayList<RoutineInfo_ItemLists> insertedAllRoutineInfo, RoutineDB routineDB, String CalendarDate) {
        this.context = context;
        this.insertedAllRoutineInfo = insertedAllRoutineInfo;
        this.routineDB = routineDB;
        this.CalendarDate = CalendarDate;
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
        holder.dbID = data.getNewRoutineObj().getId();
        holder.title.setText(data.getNewRoutineObj().getRoutineTitle());
        holder.date.setText(data.getNewRoutineObj().getRoutineDate());

        holder.btDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int delId = data.getNewRoutineObj().getId();
                Log.d("Deleted Routine", data.toString());

                routineDB.newRoutineDao().deleteNewRoutineObj(delId);

                if (CalendarDate != null)
                    insertedAllRoutineInfo = (ArrayList<RoutineInfo_ItemLists>) routineDB.routineInfoDao().getRoutineInfoListbyDate(CalendarDate);
                else
                    insertedAllRoutineInfo = (ArrayList<RoutineInfo_ItemLists>) routineDB.routineInfoDao().getRoutineInfoList();

                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return insertedAllRoutineInfo.size();
    }

    // <22.12.11> 어뎁터 밖에서도 리스트값 사용하기 위함
    public interface OnItemClickListener{
        void onItemClick(int id, int pos);
    }
    // <22.12.11> 외부에서 오버라이딩한 커스텀 리스너를 할당 메서드
    private OnItemClickListener routineClick = null;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.routineClick = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id, title, date;
        ImageView btEdit, btDelete;
        int dbID;

        public ViewHolder(View itemView){
            super(itemView);
            id = itemView.findViewById(R.id.ID1);
            title = itemView.findViewById(R.id.routineTitle1);
            date = itemView.findViewById(R.id.routineDate);
            btEdit = itemView.findViewById(R.id.bt_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(routineClick != null){
                            routineClick.onItemClick(dbID, pos);
                        }
                    }
                }
            });
        }
    }
}
