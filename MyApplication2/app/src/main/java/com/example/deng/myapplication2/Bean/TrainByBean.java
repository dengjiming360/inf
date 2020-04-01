package com.example.deng.myapplication2.Bean;

public class TrainByBean {
    public String arrive;
    public String arrivetime;
    public String bianhao;
    public String date;
    public String end;
    public String leave;
    public String leavetime;
    public String start;
    String trainno;

    public TrainByBean(String trainno, String bianhao, String start, String end, String leave, String arrive, String leavetime, String arrivetime, String date) {
        this.trainno = trainno;
        this.bianhao = bianhao;
        this.start = start;
        this.end = end;
        this.leave = leave;
        this.arrive = arrive;
        this.leavetime = leavetime;
        this.arrivetime = arrivetime;
        this.date = date;
    }

    public String getTrainno() {
        return this.trainno;
    }

    public void setTrainno(String trainno) {
        this.trainno = trainno;
    }

    public String getBianhao() {
        return this.bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getLeave() {
        return this.leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
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

    public String getArrivetime() {
        return this.arrivetime;
    }

    public void setArrivetime(String arrivetime) {
        this.arrivetime = arrivetime;
    }

    public String getLeavetime() {
        return this.leavetime;
    }

    public void setLeavetime(String leavetime) {
        this.leavetime = leavetime;
    }
}
