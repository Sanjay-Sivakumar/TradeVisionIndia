package com.example.notificationappmsg;

public class ListEquipments {

    private String name,number;

    public ListEquipments(){

    }

    public ListEquipments(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
