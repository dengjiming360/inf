package com.example.deng.myapplication2.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase2 extends SQLiteOpenHelper {
    public static final String CREATE_STATION = "create table stationmessage( id integer primary key autoincrement,  enname text, stationname text, stationbianhao text, stationpinyin text, stationjianpin text,  num text)";

    public DataBase2(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_STATION);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}