package com.example.deng.myapplication2.Application;

import android.app.Application;
import android.content.Context;


public class MyApplication extends Application {
    public static Context context;

    public static Context getContext() {
        return context;
    }

}
