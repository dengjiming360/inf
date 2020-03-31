package com.example.deng.myapplication2.Bean;

public class StaBean {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJianpin() {
        return jianpin;
    }

    public void setJianpin(String jianpin) {
        this.jianpin = jianpin;
    }

    public StaBean(String name, String jianpin) {
        this.name = name;
        this.jianpin = jianpin;
    }

    public String name;
    public String jianpin;
}
