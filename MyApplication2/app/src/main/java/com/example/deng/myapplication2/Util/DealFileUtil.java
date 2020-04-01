package com.example.deng.myapplication2.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class DealFileUtil {
    public static ArrayList<Integer> seekposition(String putstr, String index, ArrayList<Integer> ind, int begin) {
        int mydex=begin;
        if(putstr.substring(begin, begin+index.length()-1).equals(index)) {
            ind.add(mydex);
        }
        while(mydex<putstr.length()) {
            int dex=putstr.indexOf(index, mydex);
            mydex=dex;
            if(mydex!=-1) {
                ind.add(mydex);
                mydex=mydex+1;
            }
            if(mydex==-1) {
                break;
            }
        }
        return ind;
    }
    public static ArrayList getSingle(ArrayList list) {
        ArrayList tempList = new ArrayList();
        Iterator it = list.iterator();
        while(it.hasNext()) {
            Object obj = it.next();
            if(!tempList.contains(obj)) {
                tempList.add(obj);
            }
        }
        return tempList;
    }
    public static ArrayList<Integer> bubble(ArrayList<Integer> list){
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 1; j < list.size() - i; j++) {
                Integer a;
                if (list.get(j - 1).compareTo(list.get(j)) > 0) {
                    a = list.get(j - 1);
                    list.set((j - 1), list.get(j));
                    list.set(j, a);
                }
            }
        }
        return list;
    }
    public static ArrayList<String> divider(InputStream inputStream,ArrayList<Integer> in,ArrayList<String> divider,String symbol){
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader buf = new BufferedReader(inputStreamReader);
        StringBuffer strb=new StringBuffer();
        String c="";
        String temp="";
        try{
            while(buf.read()!=-1) {
                c = buf.readLine();
                strb.append(c);
                in = DealFileUtil.seekposition(c, symbol, in, 0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        in=DealFileUtil.getSingle(in);
        in=DealFileUtil.bubble(in);
        for(int abc=0;abc<in.size();abc++) {
            if(abc<in.size()-1){
                if(abc==0) {
                    temp=c.substring(0,in.get(abc));
                    divider.add(temp);
                }
                temp=c.substring(in.get(abc)+1,in.get(abc+1));
            }
            if(abc==in.size()-1) {
                temp=c.substring(in.get(abc)+1);
            }
            divider.add(temp);
        }
        return divider;
    }
}