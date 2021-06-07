package com.example.notificationappmsg;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class SearchServiceDetails {



    private String service_company_id,service_id_Ins,service_company_name,service_company_phone,service_company_email,service_company_address;

    public SearchServiceDetails(){

    }

    public SearchServiceDetails(String service_company_id, String service_id_Ins, String service_company_name, String service_company_phone, String service_company_email, String service_company_address) {
        this.service_company_id = service_company_id;
        this.service_id_Ins = service_id_Ins;
        this.service_company_name = service_company_name;
        this.service_company_phone = service_company_phone;
        this.service_company_email = service_company_email;
        this.service_company_address = service_company_address;
    }

    public String getService_company_id() {
        return service_company_id;
    }

    public void setService_company_id(String service_company_id) {
        this.service_company_id = service_company_id;
    }

    public String getService_id_Ins() {
        return service_id_Ins;
    }

    public void setService_id_Ins(String service_id_Ins) {
        this.service_id_Ins = service_id_Ins;
    }

    public String getService_company_name() {
        return service_company_name;
    }

    public void setService_company_name(String service_company_name) {
        this.service_company_name = service_company_name;
    }

    public String getService_company_phone() {
        return service_company_phone;
    }

    public void setService_company_phone(String service_company_phone) {
        this.service_company_phone = service_company_phone;
    }

    public String getService_company_email() {
        return service_company_email;
    }

    public void setService_company_email(String service_company_email) {
        this.service_company_email = service_company_email;
    }

    public String getService_company_address() {
        return service_company_address;
    }

    public void setService_company_address(String service_company_address) {
        this.service_company_address = service_company_address;
    }
}
