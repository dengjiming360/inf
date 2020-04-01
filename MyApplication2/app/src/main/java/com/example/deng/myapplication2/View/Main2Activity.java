package com.example.deng.myapplication2.View;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.deng.myapplication2.Adapter.MyAdapter1;
import com.example.deng.myapplication2.Bean.TrainByBean;
import com.example.deng.myapplication2.IView.IView2;
import com.example.deng.myapplication2.Presenter.Presenter_GETBY;
import com.example.deng.myapplication2.R;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements IView2 {
    String arrive;
    Intent intent;
    String leave;
    MyAdapter1 myAdapter1;
    public RecyclerView rcv1;
    ArrayList<TrainByBean> trainByBeans = new ArrayList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main2);
        this.rcv1 = (RecyclerView) findViewById(R.id.rcv1);
        this.intent = getIntent();
        this.leave = this.intent.getStringExtra("leave");
        this.arrive = this.intent.getStringExtra("arrive");
        new Presenter_GETBY(this, this).setData(this.rcv1, this.leave, this.arrive);
    }

    public void Success(ArrayList<TrainByBean> trainByBeanArrayList, RecyclerView rcv2) {
        rcv2.setLayoutManager(new LinearLayoutManager(this));
        this.myAdapter1 = new MyAdapter1(trainByBeanArrayList, this);
        rcv2.setAdapter(this.myAdapter1);
        rcv2.addItemDecoration(new DividerItemDecoration(this, 1));
    }
}