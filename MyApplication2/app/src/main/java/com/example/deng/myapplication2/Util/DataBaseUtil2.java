package com.example.deng.myapplication2.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.deng.myapplication2.Database.DataBase2;
import com.example.deng.myapplication2.Bean.StaBean;
import com.example.deng.myapplication2.Bean.StationBean;

import java.util.ArrayList;

public class DataBaseUtil2 {
    public static SQLiteDatabase PiliangIsert(DataBase2 dataBase2, ArrayList<StationBean> stationbeans) {
        SQLiteDatabase sqlite1 = dataBase2.getWritableDatabase();
        ArrayList<ContentValues> vs = new ArrayList<ContentValues>();
        try {
            for (int i = 0; i < stationbeans.size(); i++) {
                ContentValues v = new ContentValues();
                v.put("enname", stationbeans.get(i).enname);
                v.put("stationname", stationbeans.get(i).stationname);
                v.put("stationbianhao", stationbeans.get(i).stationbianhao);
                v.put("stationpinyin", stationbeans.get(i).stationpinyin);
                v.put("stationjianpin", stationbeans.get(i).stationjianpin);
                v.put("num", stationbeans.get(i).num);
                vs.add(v);
            }
            sqlite1.beginTransaction();
            for (int j = 0; j < vs.size(); j++) {
                sqlite1.insert("stationmessage", null, vs.get(j));
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
    public static int select(DataBase2 dataBase2){
        SQLiteDatabase sqlite2=dataBase2.getWritableDatabase();
        int count=-1;
        if(sqlite2.isOpen()){
            Cursor cursor=sqlite2.rawQuery("select * from stationmessage",null);
            count=cursor.getCount();
            cursor.close();
        }
        sqlite2.close();
        return count;
    }
    public static String select(DataBase2 dataBase2,int pos){
        SQLiteDatabase sqlite2=dataBase2.getWritableDatabase();
        String record=null;
        if(sqlite2.isOpen()){
            Cursor cursor=sqlite2.rawQuery("select * from stationmessage where id = ?",new String[]{String.valueOf(pos)});
            while (cursor.moveToNext()) {
                record = cursor.getString(1)+"#"+cursor.getString(2) + "#" + cursor.getString(3) + "#" + cursor.getString(4)+"#"+
                        cursor.getString(5)+"#"+cursor.getString(6);
            }
            cursor.close();
        }
        sqlite2.close();
        return record;
    }
    public static String selectstation(DataBase2 dataBase2,String bianhao){
        SQLiteDatabase sqlite2=dataBase2.getWritableDatabase();
        String record=null;
        if(sqlite2.isOpen()){
            Cursor cursor=sqlite2.rawQuery("select * from stationmessage where stationbianhao =?",new String[]{String.valueOf(bianhao)});
            while (cursor.moveToNext()) {
                record = cursor.getString(cursor.getColumnIndex("stationname"));
            }
            cursor.close();
        }
        sqlite2.close();
        return record;
    }
    public static String selectInvstation(DataBase2 dataBase2,String name){
        SQLiteDatabase sqlite2=dataBase2.getWritableDatabase();
        String record=null;
        if(sqlite2.isOpen()){
            Cursor cursor=sqlite2.rawQuery("select * from stationmessage where stationname =?",new String[]{String.valueOf(name)});
            while (cursor.moveToNext()) {
                record = cursor.getString(cursor.getColumnIndex("stationbianhao"));
            }
            cursor.close();
        }
        sqlite2.close();
        return record;
    }
    public static void delete(DataBase2 dataBase2, Context context){
        SQLiteDatabase db = dataBase2.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("delete from stationmessage");
            db.execSQL("DELETE FROM sqlite_sequence WHERE name = 'stationmessage'");
            context.deleteDatabase("stationmessage.db");
            db.close();
        }
    }
    public static String check(DataBase2 dataBase2,String columnname,String result){
        SQLiteDatabase db= dataBase2.getWritableDatabase();
        int count=-1;
        String bh=null;
        Cursor cursor=db.rawQuery("select stationbianhao from stationmessage where "+columnname+" = ?",new String[]{String.valueOf(result)});
        if(db.isOpen()){
            while(cursor.moveToNext()){
                bh=cursor.getString(cursor.getColumnIndex("stationbianhao"));
            }
        }
        cursor.close();
        return bh;
    }
    public static ArrayList<StaBean> mohuname(DataBase2 dataBase2, String guanjianzi, String keyword){
        SQLiteDatabase db= dataBase2.getWritableDatabase();
        int count=-1;
        String bh=null;
        String ah=null;
        ArrayList<StaBean>allresult=new ArrayList<StaBean>();
        String sql2 = "select stationname,stationjianpin from stationmessage where "+guanjianzi+"  like '"+keyword+"%'";
        Cursor cursor2 = db.rawQuery(sql2,null);
        if(db.isOpen()){

            while(cursor2.moveToNext()){
                bh=cursor2.getString(cursor2.getColumnIndex("stationname"));
                ah=cursor2.getString(cursor2.getColumnIndex("stationjianpin"));
                allresult.add(new StaBean(bh,ah));
            }
        }
        cursor2.close();
        return allresult;
    }
}
