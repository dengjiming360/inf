package com.example.deng.myapplication2.Bean;

public class StationBean
{
    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public String getStationbianhao() {
        return stationbianhao;
    }

    public void setStationbianhao(String stationbianhao) {
        this.stationbianhao = stationbianhao;
    }

    public String getStationpinyin() {
        return stationpinyin;
    }

    public void setStationpinyin(String stationpinyin) {
        this.stationpinyin = stationpinyin;
    }

    public String getStationjianpin() {
        return stationjianpin;
    }

    public void setStationjianpin(String stationjianpin) {
        this.stationjianpin = stationjianpin;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String enname;

    public StationBean(String enname, String stationname, String stationbianhao, String stationpinyin, String stationjianpin, String num) {
        this.enname = enname;
        this.stationname = stationname;
        this.stationbianhao = stationbianhao;
        this.stationpinyin = stationpinyin;
        this.stationjianpin = stationjianpin;
        this.num = num;
    }

    public String stationname;
    public String stationbianhao;
    public String stationpinyin;
    public String stationjianpin;
    public String num;
}
