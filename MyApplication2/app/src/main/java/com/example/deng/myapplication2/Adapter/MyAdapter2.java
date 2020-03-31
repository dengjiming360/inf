package com.example.deng.myapplication2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deng.myapplication2.R;
import com.example.deng.myapplication2.Bean.StaBean;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.VH> {
    ArrayList<StaBean> staBeans=new ArrayList<StaBean>();
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.myadapter2_layout2,parent,false);
        return new MyAdapter2.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
         holder.tv1.setText(staBeans.get(position).name);
         holder.tv2.setText(staBeans.get(position).jianpin);
    }

    public MyAdapter2(ArrayList<StaBean> staBeans){
        this.staBeans=staBeans;
    }
    @Override
    public int getItemCount() {
        return staBeans.size();
    }
    public void addData(int position,StaBean staBean){
        staBeans.add(staBean);
        notifyItemInserted(position);
    }
    public void removeData(int position){
        staBeans.remove(staBeans.get(position));
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
    public void removeAll(){
        for(int i=0;i<staBeans.size();i++){
            staBeans.remove(staBeans.get(i));
            notifyItemRemoved(i);
            notifyDataSetChanged();
        }
    }
    static class VH extends RecyclerView.ViewHolder{
     TextView tv1,tv2;
        public VH(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
        }
    }
}
