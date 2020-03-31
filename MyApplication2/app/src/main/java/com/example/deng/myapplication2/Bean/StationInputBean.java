package com.example.deng.myapplication2.Bean;

public class StationInputBean {
    String train_no;

    public StationInputBean(String train_no, String start, String arrive, String date) {
        this.train_no = train_no;
        this.start = start;
        this.arrive=arrive;
        this.date = date;
    }

    String start;

    public String getTrain_no() {
        return train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String arrive;
    String date;
}
