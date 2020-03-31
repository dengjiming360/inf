package com.example.deng.myapplication2.Bean;

public class TrainBean {
    private String station_train_code;

    public TrainBean(String date,String station_train_code,  String train_no) {
        this.station_train_code = station_train_code;
        this.date = date;
        this.train_no = train_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;
    public String getStation_train_code() {
        return station_train_code;
    }

    public void setStation_train_code(String station_train_code) {
        this.station_train_code = station_train_code;
    }

    public String getTrain_no() {
        return train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    private String train_no;

}
