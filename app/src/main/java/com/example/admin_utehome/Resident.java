package com.example.admin_utehome;

public class Resident {
    private String name;
    private String roomInfo;
    
    public Resident(String name, String roomInfo) {
        this.name = name;
        this.roomInfo = roomInfo;
    }

    public String getName() { return name; }
    public String getRoomInfo() { return roomInfo; }
}
