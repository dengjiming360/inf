package com.example.deng.myapplication2.Bean;

public class StationBean {
    public String enname;
    public String num;
    public String stationbianhao;
    public String stationjianpin;
    public String stationname;
    public String stationpinyin;

    public String getEnname() {
        return this.enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getStationname() {
        return this.stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public String getStationbianhao() {
        return this.stationbianhao;
    }

    public void setStationbianhao(String stationbianhao) {
        this.stationbianhao = stationbianhao;
    }

    public String getStationpinyin() {
        return this.stationpinyin;
    }

    public void setStationpinyin(String stationpinyin) {
        this.stationpinyin = stationpinyin;
    }

    public String getStationjianpin() {
        return this.stationjianpin;
    }

    public void setStationjianpin(String stationjianpin) {
        this.stationjianpin = stationjianpin;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public StationBean(String enname, String stationname, String stationbianhao, String stationpinyin, String stationjianpin, String num) {
        this.enname = enname;
        this.stationname = stationname;
        this.stationbianhao = stationbianhao;
        this.stationpinyin = stationpinyin;
        this.stationjianpin = stationjianpin;
        this.num = num;
    }
}