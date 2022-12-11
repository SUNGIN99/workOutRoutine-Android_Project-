package com.example.workoutroutine.recyclerviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutroutine.R;

import java.util.ArrayList;

public class SelectWorkout_Adapter_grid extends RecyclerView.Adapter<SelectWorkout_Adapter_grid.select_workout_ViewHolder> {

    Context context;
    ArrayList<Select_workoutItem> list;

    public SelectWorkout_Adapter_grid(Context context, ArrayList<Select_workoutItem> list){
        super();
        this.context= context;
        this.list = list;
    }

    // 뷰 홀더 바인딩하기
    @Override
    public void onBindViewHolder(select_workout_ViewHolder holder, int position){
        holder.image.setImageResource(list.get(position).image);
        holder.name.setText(list.get(position).name);
    }

    // 뷰홀더를 레이아웃에 띄워서 하나의 뷰로 띄우고 onBindViewHolder에서 값을 채워서 사용
    @Override
    public select_workout_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.select_workout_item, parent, false);
        return new select_workout_ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    // <22.11.12> 어뎁터 밖에서도 그리드 리스트의 값을 사용하기위해 커스텀 리스너 생성 (이미지버튼)
    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }

    // <22.11.12> 외부에서 오버라이딩한 커스텀 리스너를 할당 메서드
    private OnItemClickListener imageBtnClick = null;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.imageBtnClick = listener;
    }

    public class select_workout_ViewHolder extends RecyclerView.ViewHolder{

        ImageButton image;
        TextView name;

        public select_workout_ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageButton);
            name = itemView.findViewById(R.id.workoutName);

            // <22.11.12>커스텀 리스너 할당 사용
            image.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(imageBtnClick != null){
                            imageBtnClick.onItemClick(name, pos);
                        }
                    }
                }
            });
        }
    }



}
