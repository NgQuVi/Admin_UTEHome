package com.example.admin_utehome;

public class Apartment {
    private String building;
    private String number;
    private String owner;
    private String area;
    private boolean isOccupied;

    public Apartment(String building, String number, String owner, String area, boolean isOccupied) {
        this.building = building;
        this.number = number;
        this.owner = owner;
        this.area = area;
        this.isOccupied = isOccupied;
    }

    public String getBuilding() { return building; }
    public String getNumber() { return number; }
    public String getOwner() { return owner; }
    public String getArea() { return area; }
    public boolean isOccupied() { return isOccupied; }
}
