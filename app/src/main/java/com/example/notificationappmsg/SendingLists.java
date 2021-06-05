package com.example.notificationappmsg;

import java.util.ArrayList;

public class SendingLists {
    String tph,stage,brand,YearofSupply;
    ArrayList<Cricketer> liststring;

    public SendingLists(String tph,String stage,String brand,String Yearofsupply, ArrayList<Cricketer> liststring) {
        this.tph=tph;
        this.stage=stage;
        this.brand=brand;
        this.YearofSupply=Yearofsupply;
        this.liststring = liststring;
    }

    public String getTph() {
        return tph;
    }

    public void setTph(String tph) {
        this.tph = tph;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public ArrayList<Cricketer> getListstring() {
        return liststring;
    }

    public String getYearofSupply() {
        return YearofSupply;
    }

    public void setYearofSupply(String yearofSupply) {
        YearofSupply = yearofSupply;
    }

    public void setListstring(ArrayList<Cricketer> liststring) {
        this.liststring = liststring;
    }
}
