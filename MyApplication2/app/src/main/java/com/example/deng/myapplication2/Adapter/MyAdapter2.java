package com.example.deng.myapplication2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.deng.myapplication2.Bean.StaBean;
import com.example.deng.myapplication2.R;
import java.util.ArrayList;

public class MyAdapter2 extends Adapter<MyAdapter2.VH> {
    ArrayList<StaBean> staBeans = new ArrayList();

    static class VH extends ViewHolder {
        TextView tv1;
        TextView tv2;

        public VH(View itemView) {
            super(itemView);
            this.tv1 = (TextView) itemView.findViewById(R.id.tv1);
            this.tv2 = (TextView) itemView.findViewById(R.id.tv2);
        }
    }

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.myadapter2_layout2, parent, false));
    }

    public void onBindViewHolder(VH holder, int position) {
        holder.tv1.setText(((StaBean) this.staBeans.get(position)).name);
        holder.tv2.setText(((StaBean) this.staBeans.get(position)).jianpin);
    }

    public MyAdapter2(ArrayList<StaBean> staBeans) {
        this.staBeans = staBeans;
    }

    public int getItemCount() {
        return this.staBeans.size();
    }

    public void addData(int position, StaBean staBean) {
        this.staBeans.add(staBean);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        ArrayList arrayList = this.staBeans;
        arrayList.remove(arrayList.get(position));
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void removeAll() {
        for (int i = 0; i < this.staBeans.size(); i++) {
            ArrayList arrayList = this.staBeans;
            arrayList.remove(arrayList.get(i));
            notifyItemRemoved(i);
            notifyDataSetChanged();
        }
    }
}
