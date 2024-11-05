package com.example.whatsupdog;

public class Chat {
    private int profile;
    private String name;
    private int status;
    private String content;
    private String time;
    private String unread;

    public Chat(int profile, String name, int status, String content, String time, String unread) {
        this.profile = profile;
        this.name = name;
        this.status = status;
        this.content = content;
        this.time = time;
        this.unread = unread;
    }

    public int getProfile() {
        return profile;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getUnread() {
        return unread;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUnread(String unread) {
        this.unread = unread;
    }
}
