package com.example.deng.myapplication2.Presenter;

import android.content.Context;

import com.example.deng.myapplication2.Bean.StationBean;
import com.example.deng.myapplication2.Bean.TrainBean;
import com.example.deng.myapplication2.IView.IView;
import com.example.deng.myapplication2.Model.Model_GETDATA;

import java.util.ArrayList;

public class Presenter_GETDATA implements Model_GETDATA.OnFinish {
    Model_GETDATA model_getdata;
    IView iView;
    public Presenter_GETDATA(IView iView,Context context){
        this.iView=iView;
        model_getdata=new Model_GETDATA(this,context);
    }
    public void setData(){
        model_getdata.train();
    }

    @Override
    public void showTrains(ArrayList<TrainBean> trainmessage) {
        iView.Success(trainmessage);
    }

    @Override
    public void showStations(ArrayList<StationBean> stationmessage) {
        iView.Success2(stationmessage);
    }
}
