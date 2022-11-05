package com.example.workoutroutine;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder>{
    private ArrayList<String> arrayList;

    public Adapter(){
        arrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        // RecyclerView는 ViewHolder를 새로 만들어야할때마다 이 메소드를 호출 함.
        // 이 메서드는 ViewHolder와 그에 연겨된 뷰를 생성하고 초기화하지만, 뷰의 콘텐츠를 채우지는 않는다.
        // ViewHoldr 가 아직 특정 데이터에 바인딩 된 상태가 아니기 때문이다.
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_list, parent, false);

        ViewHolder viewholder = new ViewHolder(context, view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        // RecyclerView는 ViewHolder를 데이터와 연결할 때 이 메소드를 호출한다.
        // 이 메소드는 적절한 데이터를 가져와서 ViewHolder의 레이아웃을 채운다.
        // 예를 들어 RecyclerView가 이름 리스트를 표시하는 경우,
        // 이 메서드는 리스트에서 적절한 이름을 찾아 ViewHolder의 TextView위젯을 채울것이다.
        String text = arrayList.get(position);
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount(){
        return arrayList.size();
    }

    public void setArrayData(String strData){
        arrayList.add(strData);
    }

}
