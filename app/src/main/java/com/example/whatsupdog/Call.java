package com.example.whatsupdog;

public class Call {
    private int profile;
    private String name;
    private int inout;
    private String datetime;

    public Call(int profile, String name, int inout, String datetime) {
        this.profile = profile;
        this.name = name;
        this.inout = inout;
        this.datetime = datetime;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInout() {
        return inout;
    }

    public void setInout(int inout) {
        this.inout = inout;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}


