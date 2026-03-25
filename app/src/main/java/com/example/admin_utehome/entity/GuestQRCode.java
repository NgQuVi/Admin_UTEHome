package com.example.admin_utehome.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "guest_qr_codes")
public class GuestQRCode {
    @PrimaryKey
    @NonNull
    public String id = "";
    public int accountId;
    public String validFrom;
    public String validUntil;
    public String status;
    public String createdAt;
}
