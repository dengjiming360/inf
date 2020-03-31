package com.example.deng.myapplication2.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deng.myapplication2.Bean.TrainByBean;
import com.example.deng.myapplication2.Database.DataBase2;
import com.example.deng.myapplication2.R;
import com.example.deng.myapplication2.Util.DataBaseUtil2;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.VH> {
ArrayList<TrainByBean>trainByBeans=new ArrayList<TrainByBean>();
Context context;
String startstation,endstation,leavestation,arrivestation;
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.myadapter1_layout1,parent,false);
        return new MyAdapter1.VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        DataBase2 dataBase2 = new DataBase2(context, "stationmessage.db", null, 1);
         startstation= DataBaseUtil2.selectstation(dataBase2,trainByBeans.get(position).start);
      endstation= DataBaseUtil2.selectstation(dataBase2,trainByBeans.get(position).end);
        leavestation= DataBaseUtil2.selectstation(dataBase2,trainByBeans.get(position).leave);
        arrivestation= DataBaseUtil2.selectstation(dataBase2,trainByBeans.get(position).arrive);
         holder.txt1.setText(trainByBeans.get(position).bianhao);
         holder.txt2.setText("车次："+trainByBeans.get(position).bianhao+"("+startstation+"—"+endstation+")");
         holder.txt2.setTextColor(Color.BLUE);
         holder.txt3.setText("出发地："+leavestation);
         holder.txt4.setText("目的地："+arrivestation);
         holder.txt5.setText("发车时间  "+trainByBeans.get(position).leavetime);

    }
    public MyAdapter1(ArrayList<TrainByBean> trainByBeans,Context context){
        this.trainByBeans=trainByBeans;
        this.context=context;
    }
    @Override
    public int getItemCount() {
        return trainByBeans.size();
    }
    static class VH extends RecyclerView.ViewHolder{
      TextView txt1,txt2,txt3,txt4,txt5;
        public VH(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.txt1);
            txt2=itemView.findViewById(R.id.txt2);
            txt3=itemView.findViewById(R.id.txt3);
            txt4=itemView.findViewById(R.id.txt4);
            txt5=itemView.findViewById(R.id.txt5);

        }
    }
}
