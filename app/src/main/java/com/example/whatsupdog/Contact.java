package com.example.whatsupdog;

public class Contact {
    private String profile;
    private String name;

    public Contact(String profile, String name) {
        this.profile = profile;
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
