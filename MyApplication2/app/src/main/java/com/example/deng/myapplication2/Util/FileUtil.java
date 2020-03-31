package com.example.deng.myapplication2.Util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.example.deng.myapplication2.Bean.TrainBean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtil {
    public static void copywrite(String srcString, String destString) throws IOException {
        FileInputStream fin=new FileInputStream(new File(srcString));
        Scanner sc=new Scanner(fin,"utf-8");
        BufferedWriter bw = new BufferedWriter(new FileWriter(destString));
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            if ((line.equals("\r"))) {
                line.replace("\r", "");
            } else if (line.equals("\t")) {
                line.replace("\t", "");
            } else if (line.equals("\n")) {
                line.replace("\n", "");
            } else if (line.equals(" ")) {
                line.replace(" ", "");
            } else if (line.equals("\r\n")) {
                line.replace("\r\n", "");
            } else if (line.equals("\n\r")) {
                line.replace("\n\r", "");
            }else if(line.equals("\"")){
                line.replace("\"","\\\"");
            }
            else{
                bw.write(line);
                 bw.newLine();
                 bw.flush();
            }
        }
         bw.close();
        fin.close();
    }
    public static boolean replaceFileStr(String filepath,String sourceStr,String targetStr){
        try {
            FileReader fis = new FileReader(filepath);
            char[] data = new char[1024];
            int rn = 0;
            StringBuilder sb=new StringBuilder();
            while ((rn = fis.read(data)) > 0) {
                String str=String.valueOf(data,0,rn);
                sb.append(str);
            }
            fis.close();
            String str = sb.toString().replace(sourceStr, targetStr);
            FileWriter fout = new FileWriter(filepath);
            fout.write(str.toCharArray());
            fout.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void writeFile(String path, InputStream inputStream) {
        try{
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while (inputStream.read() != -1) {
                String s = bufferedReader.readLine();
                if ((s.equals("\r"))) {
                    s.replace("\r", "");
                } else if (s.equals("\t")) {
                    s.replace("\t", "");
                } else if (s.equals("\n")) {
                    s.replace("\n", "");
                } else if (s.equals(" ")) {
                    s.replace(" ", "");
                } else if (s.equals("\r\n")) {
                    s.replace("\r\n", "");
                } else if (s.equals("\n\r")) {
                    s.replace("\n\r", "");
                }
                else{
                    bufferedWriter.write(s);
                }
            }
            inputStream.close();
            bufferedReader.close();
            bufferedWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeStrAppend(String content,String path) {
            try {
                File writename = new File(path);
                BufferedWriter out = new BufferedWriter(new FileWriter(writename,true));
                out.write(content);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public static void clearInfoForFile(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeBigAppend(String path,InputStream inputStream){
        Scanner sc = null;
        try {
            FileWriter fw=new FileWriter(path,true);
            sc = new Scanner(inputStream, "utf-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if ((line.equals("\r"))) {
                    line.replace("\r", "");
                } else if (line.equals("\t")) {
                    line.replace("\t", "");
                } else if (line.equals("\n")) {
                    line.replace("\n", "");
                } else if (line.equals(" ")) {
                    line.replace(" ", "");
                } else if (line.equals("\r\n")) {
                    line.replace("\r\n", "");
                } else if (line.equals("\n\r")) {
                    line.replace("\n\r", "");
                }
                else{
                    fw.write(line,0,line.length());

                }
            }
            fw.close();
            inputStream.close();
        }catch(IOException e){

        }
        if (sc != null) {
            sc.close();
        }
    }
    public static void CreateEmptyFile(String path){
        File file=new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void writeBig(String path,InputStream inputStream,boolean append){
        Scanner sc = null;
        File file=new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
        FileWriter fw=new FileWriter(path,append);
            sc = new Scanner(inputStream, "utf-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if ((line.equals("\r"))) {
                    line.replace("\r", "");
                } else if (line.equals("\t")) {
                    line.replace("\t", "");
                } else if (line.equals("\n")) {
                    line.replace("\n", "");
                } else if (line.equals(" ")) {
                    line.replace(" ", "");
                } else if (line.equals("\r\n")) {
                    line.replace("\r\n", "");
                } else if (line.equals("\n\r")) {
                    line.replace("\n\r", "");
                }
                else{
                    fw.write(line,0,line.length());

                }
            }
            fw.close();
            inputStream.close();
        }catch(IOException e){

        }
            if (sc != null) {
                sc.close();
            }
    }

    public static ArrayList<String>readToJson2(String path) {
        ArrayList<String> ts=new ArrayList<String>();
        try {
            JSONReader jsonReader = new JSONReader(new InputStreamReader(new FileInputStream(new File(path))));
            jsonReader.startObject();
            while(jsonReader.hasNext()){
                String key=jsonReader.readString();
                if(key.equals("httpstatus")){
                    String httpstatus=jsonReader.readObject().toString();
                }
                else if(key.equals("data")){
                    jsonReader.startObject();
                    while(jsonReader.hasNext()){
                        String key2=jsonReader.readString();
                        if(key2.equals("result")){
                            jsonReader.startArray();
                            while(jsonReader.hasNext()){
                                String item=jsonReader.readString();
                                ts.add(item);
                            }
                            jsonReader.endArray();
                        }
                        else if(key2.equals("flag")){
                             String flag=jsonReader.readObject().toString();
                        }
                        else if(key2.equals("map")){
                            jsonReader.startObject();
                            while(jsonReader.hasNext()){
                                String map1=jsonReader.readString();
                                String  obj=jsonReader.readObject().toString();
                            }
                            jsonReader.endObject();
                        }
                    }
                    jsonReader.endObject();
                }
                else if(key.equals("messages")){
                       String msg=jsonReader.readObject().toString();
                }
                else if(key.equals("status")){
                      String status=jsonReader.readObject().toString();
                }
            }
            jsonReader.endObject();
        }
        catch(IOException e){

        }
        return ts;
    }
    public static ArrayList<TrainBean> readToJson(String path) {
        int a=0;
        ArrayList<TrainBean> trainBeans=new ArrayList<TrainBean>();
        try{
                String reader=null;
                String reader2=null;
                String key=null;
                String value1 = null;
                String value2 = null;
                String value3=null;
                JSONReader jsonReader=new JSONReader(new InputStreamReader(new FileInputStream(new File(path))));
                jsonReader.startObject();
                while(jsonReader.hasNext()) {
                    reader = jsonReader.readString();
                    jsonReader.startObject();
                    while(jsonReader.hasNext()){
                            reader2 = jsonReader.readString();
                            jsonReader.startArray();
                            while(jsonReader.hasNext()){
                                jsonReader.startObject();
                                while(jsonReader.hasNext()){
                                        key = jsonReader.readString();
                                        if (key.equals("station_train_code")) {
                                            value1 = jsonReader.readObject().toString();
                                        }
                                        if (key.equals("train_no")) {
                                            value2 = jsonReader.readObject().toString();
                                        }
                                            a++;
                                            if(a%2==0) {
                                                trainBeans.add(new TrainBean(reader, value1, value2));
                                                a = 0;
                                            }

                                }
                                jsonReader.endObject();
                            }
                            jsonReader.endArray();
                        }
                    jsonReader.endObject();
                }
                jsonReader.endObject();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return trainBeans;
    }
    public static void deleteFile(String path){
        File file1=new File(path);
        if(file1.exists()){
            file1.delete();
        }
    }
}
