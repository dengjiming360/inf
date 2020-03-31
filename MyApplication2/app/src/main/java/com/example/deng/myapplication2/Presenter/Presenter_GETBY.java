package com.example.deng.myapplication2.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.deng.myapplication2.Bean.StationBean;
import com.example.deng.myapplication2.Bean.TrainBean;
import com.example.deng.myapplication2.Bean.TrainByBean;
import com.example.deng.myapplication2.IView.IView;
import com.example.deng.myapplication2.IView.IView2;
import com.example.deng.myapplication2.Model.Model_GETBY;
import com.example.deng.myapplication2.Model.Model_GETDATA;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.Disposable;

public class Presenter_GETBY implements Model_GETBY.OnFinish{
    Model_GETBY model_getby;
    IView2 iView2;
    public Presenter_GETBY(IView2 iView2, Context context){
        this.iView2=iView2;
        model_getby=new Model_GETBY(this,context);
    }
    public void setData(RecyclerView rcv1,String leave,String arrive){
        model_getby.trainby(rcv1,leave,arrive);
    }
    @Override
    public void showTrainBys(ArrayList<TrainByBean> trainByBeans, RecyclerView rcv1) {
        iView2.Success(trainByBeans,rcv1);
    }
}
