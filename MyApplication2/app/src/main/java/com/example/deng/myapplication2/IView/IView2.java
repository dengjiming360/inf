package com.example.deng.myapplication2.IView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.deng.myapplication2.Bean.TrainByBean;
import java.util.ArrayList;

public interface IView2 {
    void Success(ArrayList<TrainByBean> arrayList, RecyclerView recyclerView);
}