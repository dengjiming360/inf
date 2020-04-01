package com.example.deng.myapplication2.Bean;

public class TrainBean {
    private String date;
    private String station_train_code;
    private String train_no;

    public TrainBean(String date, String station_train_code, String train_no) {
        this.station_train_code = station_train_code;
        this.date = date;
        this.train_no = train_no;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStation_train_code() {
        return this.station_train_code;
    }

    public void setStation_train_code(String station_train_code) {
        this.station_train_code = station_train_code;
    }

    public String getTrain_no() {
        return this.train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }
}