package com.example.deng.myapplication2.Zujian;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MyView extends LinearLayout {


    public static int getX0() {
        return x0;
    }

    public static void setX0(int x0) {
        MyView.x0 = x0;
    }

    public static int getY0() {
        return y0;
    }

    public static void setY0(int y0) {
        MyView.y0 = y0;
    }

    public static int getW() {
        return w;
    }

    public static void setW(int w) {
        MyView.w = w;
    }

    public static int getH() {
        return h;
    }

    public static void setH(int h) {
        MyView.h = h;
    }

    static int x0,y0,w,h;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    @Override
    protected void onDraw(Canvas canvas){
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(255,0,255,255));
        canvas.drawRect(new Rect(x0,y0,w,h),paint);
    }
}
