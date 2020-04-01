package com.example.deng.myapplication2.IView;

import com.example.deng.myapplication2.Bean.StationBean;
import com.example.deng.myapplication2.Bean.TrainBean;
import java.util.ArrayList;

public interface IView {
    void Success(ArrayList<TrainBean> arrayList);

    void Success2(ArrayList<StationBean> arrayList);
}