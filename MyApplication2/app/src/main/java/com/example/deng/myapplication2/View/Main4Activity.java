package com.example.deng.myapplication2.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deng.myapplication2.R;

import com.example.deng.mycalendar.bean.DateBean;
import com.example.deng.mycalendar.listener.OnPagerChangeListener;
import com.example.deng.mycalendar.listener.OnSingleChooseListener;
import com.example.deng.mycalendar.weiget.CalendarView;


public class Main4Activity extends AppCompatActivity {
CalendarView calendarView;
Intent intent;
int D,Y,M;
double ratioH,ratioW;
int H,W;
ImageButton before,after;
LinearLayout lin1;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        intent = getIntent();
        Y = intent.getIntExtra("cyear", 0);
        M = intent.getIntExtra("cmonth", -1);
        D = intent.getIntExtra("cday", 0);
        lin1=findViewById(R.id.lin1);
        calendarView=findViewById(R.id.cal);
        textView=findViewById(R.id.month);
        before=findViewById(R.id.before);
        after=findViewById(R.id.after);
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.arrowScroll(View.FOCUS_LEFT);
            }
        });
        after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.arrowScroll(View.FOCUS_RIGHT);
            }
        });
        calendarView.setInitDate(String.valueOf(Y)+"."+String.valueOf(M)).setStartEndDate("1900.1","2100.12").setSingleDate(String.valueOf(Y)+"."+String.valueOf(M)+"."+String.valueOf(D)).init();
        textView.setText(String.valueOf(Y)+"年"+String.valueOf(M)+"月");
        calendarView.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                 textView.setText(date[0]+"年"+date[1]+"月");
            }
        });
        calendarView.setOnSingleChooseListener(new OnSingleChooseListener() {
            @Override
            public void onSingleChoose(View view, DateBean date) {

            }
        });

    }

}
