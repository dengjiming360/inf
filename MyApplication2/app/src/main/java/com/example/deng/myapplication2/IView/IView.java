package com.example.deng.myapplication2.IView;

import com.example.deng.myapplication2.Bean.StationBean;
import com.example.deng.myapplication2.Bean.TrainBean;

import java.util.ArrayList;

public interface IView {
    public void Success(ArrayList<TrainBean> trainmessage);
    public void Success2(ArrayList<StationBean> stationmessage);
}
