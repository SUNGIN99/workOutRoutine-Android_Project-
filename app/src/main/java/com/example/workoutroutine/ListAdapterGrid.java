package com.example.workoutroutine;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapterGrid extends RecyclerView.Adapter<ListAdapterGrid.MyViewHolder> {

    Context context;
    ArrayList<workoutItem> list;

    public ListAdapterGrid(Context context, ArrayList<workoutItem> list){
        super();
        this.context= context;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.image.setImageResource(list.get(position).image);
        holder.name.setText(list.get(position).name);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_grid_item, parent, false);
        return new MyViewHolder(view);
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

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageButton image;
        TextView name;

        public MyViewHolder(View itemView) {
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
