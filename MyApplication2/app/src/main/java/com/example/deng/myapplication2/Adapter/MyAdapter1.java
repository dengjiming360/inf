package com.example.deng.myapplication2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.example.deng.myapplication2.Bean.TrainByBean;
import com.example.deng.myapplication2.Database.DataBase2;
import com.example.deng.myapplication2.R;
import com.example.deng.myapplication2.Util.DataBaseUtil2;
import java.util.ArrayList;

public class MyAdapter1 extends Adapter<MyAdapter1.VH> {
    String arrivestation;
    Context context;
    String endstation;
    String leavestation;
    String startstation;
    ArrayList<TrainByBean> trainByBeans = new ArrayList();

    static class VH extends ViewHolder {
        TextView txt1;
        TextView txt2;
        TextView txt3;
        TextView txt4;
        TextView txt5;

        public VH(View itemView) {
            super(itemView);
            this.txt1 = (TextView) itemView.findViewById(R.id.txt1);
            this.txt2 = (TextView) itemView.findViewById(R.id.txt2);
            this.txt3 = (TextView) itemView.findViewById(R.id.txt3);
            this.txt4 = (TextView) itemView.findViewById(R.id.txt4);
            this.txt5 = (TextView) itemView.findViewById(R.id.txt5);
        }
    }

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.myadapter1_layout1, parent, false));
    }

    public void onBindViewHolder(VH holder, int position) {
        DataBase2 dataBase2 = new DataBase2(this.context, "stationmessage.db", null, 1);
        this.startstation = DataBaseUtil2.selectstation(dataBase2, ((TrainByBean) this.trainByBeans.get(position)).start);
        this.endstation = DataBaseUtil2.selectstation(dataBase2, ((TrainByBean) this.trainByBeans.get(position)).end);
        this.leavestation = DataBaseUtil2.selectstation(dataBase2, ((TrainByBean) this.trainByBeans.get(position)).leave);
        this.arrivestation = DataBaseUtil2.selectstation(dataBase2, ((TrainByBean) this.trainByBeans.get(position)).arrive);
        holder.txt1.setText(((TrainByBean) this.trainByBeans.get(position)).bianhao);
        TextView textView = holder.txt2;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("车次：");
        stringBuilder.append(((TrainByBean) this.trainByBeans.get(position)).bianhao);
        stringBuilder.append("(");
        stringBuilder.append(this.startstation);
        stringBuilder.append("—");
        stringBuilder.append(this.endstation);
        stringBuilder.append(")");
        textView.setText(stringBuilder.toString());
        holder.txt2.setTextColor(-16776961);
        textView = holder.txt3;
        stringBuilder = new StringBuilder();
        stringBuilder.append("出发地：");
        stringBuilder.append(this.leavestation);
        textView.setText(stringBuilder.toString());
        textView = holder.txt4;
        stringBuilder = new StringBuilder();
        stringBuilder.append("目的地：");
        stringBuilder.append(this.arrivestation);
        textView.setText(stringBuilder.toString());
        textView = holder.txt5;
        stringBuilder = new StringBuilder();
        stringBuilder.append("发车时间  ");
        stringBuilder.append(((TrainByBean) this.trainByBeans.get(position)).leavetime);
        textView.setText(stringBuilder.toString());
    }

    public MyAdapter1(ArrayList<TrainByBean> trainByBeans, Context context) {
        this.trainByBeans = trainByBeans;
        this.context = context;
    }

    public int getItemCount() {
        return this.trainByBeans.size();
    }
}