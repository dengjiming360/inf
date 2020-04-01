package com.example.deng.myapplication2.Bean;

public class StaBean {
    public String jianpin;
    public String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJianpin() {
        return this.jianpin;
    }

    public void setJianpin(String jianpin) {
        this.jianpin = jianpin;
    }

    public StaBean(String name, String jianpin) {
        this.name = name;
        this.jianpin = jianpin;
    }
}