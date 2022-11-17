package com.example.workoutroutine;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    public MainAdapter(Activity context, List<MainData> dataList)
    {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.ViewHolder holder, int position){
        final MainData data = dataList.get(position);
        database = RoomDB.getInstance(context);

        holder.textView.setText(data.getText());
        holder.btEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MainData mainData = dataList.get(holder.getAdapterPosition());

                final int sID = mainData.getId();
                String sText = mainData.getText();
                ArrayList<String> sSubList = mainData.getStringList();
                Log.d("adpater sList:", String.valueOf(sSubList));


                // dialog -------------------------------
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width, height);
                dialog.show();

                final EditText editText= dialog.findViewById(R.id.dialog_edit_text);
                final EditText subtext1 = dialog.findViewById(R.id.sub_text1);
                final EditText subtext2 = dialog.findViewById(R.id.sub_text2);
                Button bt_update = dialog.findViewById(R.id.bt_update);

                editText.setText(sText);
                subtext1.setText(sSubList.get(0));
                subtext2.setText(sSubList.get(1));

                bt_update.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        dialog.dismiss();
                        String uText = editText.getText().toString().trim();

                        database.mainDao().update(sID, uText);

                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();
                    }
                });
                // dialog -------------------------------
            }
        });

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData mainData = dataList.get(holder.getAdapterPosition());
                database.mainDao().delete(mainData);

                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, dataList.size());
            }
        });
    }

    @Override
    public int getItemCount(){
        return dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View view){
            super(view);
            textView = view.findViewById(R.id.text_view);
            btEdit = view.findViewById(R.id.bt_edit);
            btDelete = view.findViewById(R.id.bt_delete);
        }
    }


}
