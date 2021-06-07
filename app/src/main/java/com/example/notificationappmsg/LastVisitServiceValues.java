package com.example.notificationappmsg;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class LastVisitServiceValues {

    private String hour_Value,oil_Value,grease_Value,Inspection_no,Service_com_Id;

    public LastVisitServiceValues(){

    }

    public LastVisitServiceValues(String hour_Value, String oil_Value, String grease_Value, String inspection_no, String service_com_Id) {
        this.hour_Value = hour_Value;
        this.oil_Value = oil_Value;
        this.grease_Value = grease_Value;
        Inspection_no = inspection_no;
        Service_com_Id = service_com_Id;
    }

    public String getHour_Value() {
        return hour_Value;
    }

    public void setHour_Value(String hour_Value) {
        this.hour_Value = hour_Value;
    }

    public String getOil_Value() {
        return oil_Value;
    }

    public void setOil_Value(String oil_Value) {
        this.oil_Value = oil_Value;
    }

    public String getGrease_Value() {
        return grease_Value;
    }

    public void setGrease_Value(String grease_Value) {
        this.grease_Value = grease_Value;
    }

    public String getInspection_no() {
        return Inspection_no;
    }

    public void setInspection_no(String inspection_no) {
        Inspection_no = inspection_no;
    }

    public String getService_com_Id() {
        return Service_com_Id;
    }

    public void setService_com_Id(String service_com_Id) {
        Service_com_Id = service_com_Id;
    }
}
