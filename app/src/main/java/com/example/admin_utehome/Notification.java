package com.example.admin_utehome;

public class Notification {
    public static final int TYPE_MAINTENANCE = 0;
    public static final int TYPE_EVENT = 1;
    public static final int TYPE_URGENT = 2;

    private int type;
    private String title;
    private String time;
    private String recipient;

    public Notification(int type, String title, String time, String recipient) {
        this.type = type;
        this.title = title;
        this.time = time;
        this.recipient = recipient;
    }

    public int getType() { return type; }
    public String getTitle() { return title; }
    public String getTime() { return time; }
    public String getRecipient() { return recipient; }
}
