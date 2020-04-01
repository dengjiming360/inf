package com.example.deng.myapplication2.Presenter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.example.deng.myapplication2.Bean.TrainByBean;
import com.example.deng.myapplication2.IView.IView2;
import com.example.deng.myapplication2.Model.Model_GETBY;
import com.example.deng.myapplication2.Model.Model_GETBY.OnFinish;
import java.util.ArrayList;

public class Presenter_GETBY implements OnFinish {
    IView2 iView2;
    Model_GETBY model_getby;

    public Presenter_GETBY(IView2 iView2, Context context) {
        this.iView2 = iView2;
        this.model_getby = new Model_GETBY(this, context);
    }

    public void setData(RecyclerView rcv1, String leave, String arrive) {
        this.model_getby.trainby(rcv1, leave, arrive);
    }

    public void showTrainBys(ArrayList<TrainByBean> trainByBeans, RecyclerView rcv1) {
        this.iView2.Success(trainByBeans, rcv1);
    }
}
