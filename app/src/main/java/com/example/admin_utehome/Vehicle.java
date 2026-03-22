package com.example.admin_utehome;

public class Vehicle {
    public static final String STATUS_APPROVED = "ĐÃ DUYỆT";
    public static final String STATUS_PENDING = "CHỜ DUYỆT";
    
    public static final String TYPE_CAR = "Ô tô";
    public static final String TYPE_MOTORBIKE = "Xe máy";

    private String licensePlate;
    private String description;
    private String ownerName;
    private String apartmentCode;
    private String type;
    private String status;

    public Vehicle(String licensePlate, String description, String ownerName, String apartmentCode, String type, String status) {
        this.licensePlate = licensePlate;
        this.description = description;
        this.ownerName = ownerName;
        this.apartmentCode = apartmentCode;
        this.type = type;
        this.status = status;
    }

    public String getLicensePlate() { return licensePlate; }
    public String getDescription() { return description; }
    public String getOwnerName() { return ownerName; }
    public String getApartmentCode() { return apartmentCode; }
    public String getType() { return type; }
    public String getStatus() { return status; }
}
