package com.example.deng.myapplication2.Bean;

public class StationByBean {
    String stationname;

    public String getStationname() {
        return stationname;
    }

    public StationByBean(String stationname, String time) {
        this.stationname = stationname;
        this.time = time;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String time;
}
