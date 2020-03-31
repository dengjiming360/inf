package com.example.deng.myapplication2.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.deng.myapplication2.Database.DataBase;
import com.example.deng.myapplication2.Bean.TrainBean;

import java.util.ArrayList;

public class DataBaseUtil {
    public static SQLiteDatabase PiliangIsert(DataBase dataBase, ArrayList<TrainBean> trainbeans) {
        SQLiteDatabase sqlite1 = dataBase.getWritableDatabase();
        ArrayList<ContentValues> vs = new ArrayList<ContentValues>();
        try {
            for (int i = 0; i < trainbeans.size(); i++) {
                ContentValues v = new ContentValues();
                v.put("date", trainbeans.get(i).getDate());
                v.put("station_train_code", trainbeans.get(i).getStation_train_code());
                v.put("train_no", trainbeans.get(i).getTrain_no());
                vs.add(v);
            }
            sqlite1.beginTransaction();
            for (int j = 0; j < vs.size(); j++) {
                sqlite1.insert("trainmessage", null, vs.get(j));
            }
            sqlite1.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlite1.endTransaction();
            sqlite1.close();
        }
        return sqlite1;
    }
    public static int select(DataBase dataBase){
       SQLiteDatabase sqlite2=dataBase.getWritableDatabase();
       int count=-1;
       if(sqlite2.isOpen()){
           Cursor cursor=sqlite2.rawQuery("select * from trainmessage",null);
           count=cursor.getCount();
           cursor.close();
       }
       sqlite2.close();
       return count;
    }
    public static String selectpos(DataBase dataBase,int pos){
        SQLiteDatabase sqlite2=dataBase.getWritableDatabase();
        String record=null;
        if(sqlite2.isOpen()){
            Cursor cursor=sqlite2.rawQuery("select * from trainmessage where id = ?",new String[]{String.valueOf(pos)});
            while (cursor.moveToNext()) {
                    record = cursor.getString(1) + "#" + cursor.getString(2) + "#" + cursor.getString(3);
            }
            cursor.close();
        }
        sqlite2.close();
        return record;
    }
    public static void delete(DataBase dataBase, Context context){
            SQLiteDatabase db = dataBase.getWritableDatabase();
            if (db.isOpen()) {
                db.execSQL("delete from trainmessage");
                db.execSQL("DELETE FROM sqlite_sequence WHERE name = 'trainmessage'");
                context.deleteDatabase("trainmessage.db");
                db.close();
        }
    }

}
