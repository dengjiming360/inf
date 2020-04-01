package com.example.deng.myapplication2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.example.deng.myapplication2.Adapter.MyAdapter2;
import com.example.deng.myapplication2.Bean.StaBean;
import com.example.deng.myapplication2.Bean.StationBean;
import com.example.deng.myapplication2.Bean.TrainBean;
import com.example.deng.myapplication2.Database.DataBase;
import com.example.deng.myapplication2.Database.DataBase2;
import com.example.deng.myapplication2.IView.IView;
import com.example.deng.myapplication2.Listener.KeyboardChangeListener;
import com.example.deng.myapplication2.Presenter.Presenter_GETDATA;
import com.example.deng.myapplication2.R;
import com.example.deng.myapplication2.Util.DataBaseUtil;
import com.example.deng.myapplication2.Util.DataBaseUtil2;
import com.example.deng.myapplication2.Util.DensityUtil;
import com.example.deng.myapplication2.Util.HWUtil;
import com.example.deng.myapplication2.Util.SpeakUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements IView {
    TextView tv1,tv2,tv3;
    EditText edit1,edit2,edit3;
    Button btn1,btn2,btn3,btn4,btn5;
    public EventManager asr,asr2;
    ProgressDialog progressDialog;
    EventListener ev1,ev2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferencessettings;
    SharedPreferences.Editor editorsettings;
    int Y,M,S,D;
    boolean settings=false;
    boolean settings2=false;
    PopupWindow popupWindow;
    PopupWindow popupWindow2;
    Context mContext;
    boolean keyboardshow=false;
    RecyclerView rcv1,rcv2;
    MyAdapter2 myAdapter2,myAdapter3;
    boolean dismiss=false;
    boolean dismiss2=false;
    boolean init=false;
    boolean init2=false;
    int ye,mo,da;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        A.start();
        sharedPreferences=MainActivity.this.getSharedPreferences("times",MODE_PRIVATE);
        Y=sharedPreferences.getInt("cyear",0);
        M=sharedPreferences.getInt("cmonth",0);
        D=sharedPreferences.getInt("cday",0);
        S=sharedPreferences.getInt("csecond",0);
        sharedPreferencessettings=MainActivity.this.getSharedPreferences("settings",MODE_PRIVATE);
        keyboardshow=sharedPreferences.getBoolean("keyboardshow",false);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        edit3=findViewById(R.id.edit3);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        edit3.setEnabled(false);
        edit3.setTextColor(Color.BLACK);
        DataBase2 dataBase2=new DataBase2(this,"stationmessage.db", null, 1);
        mContext=this;
        edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dismiss=true;
                showPopupView(edit1);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dismiss=false;
                showPopupView(edit1);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
        edit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dismiss2=true;
                showPopupView2(edit2);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dismiss2=false;
                showPopupView2(edit2);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
        edit1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(init==false){
                    init=true;
                }
                if(b==true){
                    dismiss=false;
                    showPopupView(edit1);
                    dismiss2=true;
                    showPopupView2(edit2);
                }
                if(b==false){
                    dismiss=true;
                    showPopupView(edit1);
                    dismiss2=false;
                    showPopupView2(edit2);
                }
            }
        });
        edit2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(init2==false){
                    init2=true;
                }
                if(b==false){
                    dismiss2=true;
                    showPopupView2(edit2);
                    dismiss=false;
                    showPopupView(edit1);
                }
                if(b==true){
                    dismiss2=false;
                    showPopupView2(edit2);
                    dismiss=true;
                    showPopupView(edit1);
                }
            }
        });
        ev1=new EventListener() {
            @Override
            public void onEvent(String s, String s1, byte[] bytes, int i, int i1) {
                String resultTxt = null;
                if (s.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)){//识别结果参数
                    if (s1.contains("\"final_result\"")){//语义结果值
                        try {
                            JSONObject json = new JSONObject(s1);
                            String result = json.getString("best_result");//取得key的识别结果
                            resultTxt = result;
                            Log.e("铁木真",resultTxt+"");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (resultTxt != null){
                    resultTxt += "\n";
                    Log.e("窝阔台",resultTxt+"");
                    edit1.append(resultTxt);
                }
            }
        };
        new KeyboardChangeListener(this).setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                Log.e("软键盘是否显示",String.valueOf(isShow));
                sharedPreferencessettings=MainActivity.this.getSharedPreferences("settings",MODE_PRIVATE);
                editorsettings=sharedPreferencessettings.edit();
                editorsettings.putBoolean("keyboardshow",isShow);
                editorsettings.commit();
            }
        });
        ev2=new EventListener() {
            @Override
            public void onEvent(String s, String s1, byte[] bytes, int i, int i1) {
                String resultTxt = null;
                if (s.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)){//识别结果参数
                    if (s1.contains("\"final_result\"")){//语义结果值
                        try {
                            JSONObject json = new JSONObject(s1);
                            String result = json.getString("best_result");//取得key的识别结果
                            resultTxt = result;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (resultTxt != null){
                    resultTxt += "\n";
                    edit2.append(resultTxt);
                }
            }
        };

        asr = EventManagerFactory.create(this, "asr");
        asr.registerListener(ev1);
        asr2=EventManagerFactory.create(this,"asr");
        asr2.registerListener(ev2);
        btn1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        SpeakUtil.start(MainActivity.this, asr);
                        break;
                    case MotionEvent.ACTION_UP:
                        SpeakUtil.stop(asr);
                        break;
                    default:
                        return false;
                }

                return true;
            }
        });
        btn2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        SpeakUtil.start(MainActivity.this, asr2);
                        break;
                    case MotionEvent.ACTION_UP:
                        SpeakUtil.stop(asr2);
                        break;
                    default:
                        return false;
                }

                return true;
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("leave",edit1.getText().toString().trim());
                intent.putExtra("arrive",edit2.getText().toString().trim());
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main4Activity.class);
                sharedPreferences = MainActivity.this.getSharedPreferences("times", MODE_PRIVATE);
                ye= sharedPreferences.getInt("cyear",-1);
                mo=sharedPreferences.getInt("cmonth",-1);
                da=sharedPreferences.getInt("cday",-1);
                intent.putExtra("cyear",ye);
                intent.putExtra("cmonth",mo);
                intent.putExtra("cday",da);
                Log.e("忽必烈",ye+"#"+mo+"#"+da);
                startActivity(intent);
            }
        });
    }
    public ArrayList<StaBean> getSta(String edit){
        DataBase2 dataBase2 = new DataBase2(MainActivity.this, "stationmessage.db", null, 1);
        ArrayList<StaBean> arr1= DataBaseUtil2.mohuname(dataBase2,"stationname",edit);
        ArrayList<StaBean> arr2=DataBaseUtil2.mohuname(dataBase2,"stationpinyin",edit);
        ArrayList<StaBean> arr3=DataBaseUtil2.mohuname(dataBase2,"stationjianpin",edit);
        ArrayList<StaBean> temp=new ArrayList<StaBean>();
        temp.addAll(arr1);
        temp.addAll(arr2);
        temp.addAll(arr3);
        Iterator it = temp.iterator();
        ArrayList<StaBean> newlist=new ArrayList<>();
        while(it.hasNext()) {
            StaBean p = (StaBean)it.next();
            if(!newlist.contains(p)) {
                newlist.add(p);
            }
        }
        return newlist;
    }
    private void initedView() {
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("数据加载中，请稍后....");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

    }
    public void initPopupView(View v,int width,int height,ArrayList<StaBean> staBeans) {

        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.popup1, null);
        rcv1 = contentView.findViewById(R.id.rcv1);
        rcv1.setLayoutManager(new LinearLayoutManager(this));
        myAdapter2 = new MyAdapter2(staBeans);
        rcv1.setAdapter(myAdapter2);
        if(init==true&&edit1.getText().toString().trim().length()!=0) {
            popupWindow = new PopupWindow(contentView, width, height, false);
        }
    }
    public void initPopupView2(View v,int width,int height,ArrayList<StaBean> staBeans) {

        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.popup2, null);
        rcv2 = contentView.findViewById(R.id.rcv2);
        rcv2.setLayoutManager(new LinearLayoutManager(this));
        myAdapter3 = new MyAdapter2(staBeans);
        rcv2.setAdapter(myAdapter3);
        if(init2==true&&edit2.getText().toString().trim().length()!=0) {
            popupWindow2 = new PopupWindow(contentView, width, height, false);
        }
    }
    public void showPopupView(View v){
        if(dismiss==false) {
            if(init==true) {
                if(edit1.getText().toString().trim().length()!=0) {
                    initPopupView(edit1, HWUtil.getScreenWidth(MainActivity.this) - HWUtil.getWidth(btn1) - HWUtil.getWidth(tv2) - DensityUtil.dp2px(MainActivity.this, 36), 220, getSta(edit1.getText().toString().trim()));
                    popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.mycolor1));
                    popupWindow.showAsDropDown(v);
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setFocusable(false);
                }
            }
        }
        if(dismiss==true){
            if(popupWindow!=null) {
                popupWindow.dismiss();
            }
            popupWindow=null;
        }
    }
    public void showPopupView2(View v){
        if(dismiss2==false) {
            if (edit2.getText().toString().length() != 0) {
                initPopupView2(edit2, HWUtil.getScreenWidth(MainActivity.this) - HWUtil.getWidth(btn2) - HWUtil.getWidth(tv3) - DensityUtil.dp2px(MainActivity.this, 36), 220, getSta(edit2.getText().toString().trim()));
                popupWindow2.setBackgroundDrawable(getResources().getDrawable(R.color.mycolor1));
                popupWindow2.showAsDropDown(v, 0, 0, Gravity.BOTTOM);
                popupWindow2.setOutsideTouchable(true);
                popupWindow2.setFocusable(false);
            }
        }
        if(dismiss2==true){
            if(popupWindow2!=null) {
                popupWindow2.dismiss();
            }
            popupWindow2=null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        asr.send(SpeechConstant.ASR_CANCEL, "{}", null, 0, 0);
        asr2.send(SpeechConstant.ASR_CANCEL, "{}", null, 0, 0);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        asr.send(SpeechConstant.ASR_CANCEL, "{}", null, 0, 0);
        asr.unregisterListener(ev1);
        asr2.unregisterListener(ev2);
    }
    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.RECORD_AUDIO,}, 1);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void Success(ArrayList<TrainBean> trainmessage) {
        DataBase dataBase = new DataBase(MainActivity.this, "trainmessage.db", null, 1);
        if(trainmessage.size()== DataBaseUtil.select(dataBase)){
            progressDialog.dismiss();
        }
    }

    @Override
    public void Success2(ArrayList<StationBean> stationmessage) {

    }
    Thread A=new Thread(new Runnable() {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg=new Message();
                    msg.what=1;
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while(true);
        }
    });
    public Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1) {
                Calendar calendar=Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                sharedPreferences=MainActivity.this.getSharedPreferences("times",MODE_PRIVATE);
                if(settings==false) {
                    if(settings2==false) {
                        editor = sharedPreferences.edit();
                        editor.putInt("cyear", year);
                        editor.putInt("cmonth", month);
                        editor.putInt("cday",day);
                        editor.putInt("csecond", second);
                        editor.commit();
                        settings = true;
                    }
                }
                if(settings==true) {
                    if(settings2==false) {
                        sharedPreferences = MainActivity.this.getSharedPreferences("times", MODE_PRIVATE);
                        int cy = sharedPreferences.getInt("cyear", 0);
                        int cm = sharedPreferences.getInt("cmonth", 0);
                        if(cy!=Y||cm!=M){
                            final Presenter_GETDATA presenter_getdata = new Presenter_GETDATA(MainActivity.this, MainActivity.this);
                            presenter_getdata.setData();
                            initedView();
                        }
                        settings2 = true;
                    }
                }
            }
        }
    };
}
