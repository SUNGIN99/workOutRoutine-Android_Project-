package com.example.workoutroutine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageButton image;
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageButton);
            name = itemView.findViewById(R.id.workoutName);
        }
    }


}
