package com.example.deng.myapplication2.Bean;

public class StationInputBean {
    String arrive;
    String date;
    String start;
    String train_no;

    public StationInputBean(String train_no, String start, String arrive, String date) {
        this.train_no = train_no;
        this.start = start;
        this.arrive = arrive;
        this.date = date;
    }

    public String getTrain_no() {
        return this.train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getArrive() {
        return this.arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}