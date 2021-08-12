package com.example.notificationappmsg;

public class model
{

    String linktoupload,name,email,address,phone,WorkPosition,accesslevel,workZone,underTeritory,Gender,id,Qualification;

    public model(){

    }

    public  model(String name,String phone,String email,String linktoupload)
    {
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.linktoupload=linktoupload;
    }


    public model(String linktoupload, String name, String email, String address, String phone, String workPosition, String accesslevel, String workZone, String underTeritory, String gender, String id, String qualification) {
        this.linktoupload = linktoupload;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        WorkPosition = workPosition;
        this.accesslevel = accesslevel;
        this.workZone = workZone;
        this.underTeritory = underTeritory;
        Gender = gender;
        this.id = id;
        Qualification = qualification;
    }

    public String getLinktoupload() {
        return linktoupload;
    }

    public void setLinktoupload(String linktoupload) {
        this.linktoupload = linktoupload;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWorkPosition() {
        return WorkPosition;
    }

    public void setWorkPosition(String workPosition) {
        WorkPosition = workPosition;
    }

    public String getAccesslevel() {
        return accesslevel;
    }

    public void setAccesslevel(String accesslevel) {
        this.accesslevel = accesslevel;
    }

    public String getWorkZone() {
        return workZone;
    }

    public void setWorkZone(String workZone) {
        this.workZone = workZone;
    }

    public String getUnderTeritory() {
        return underTeritory;
    }

    public void setUnderTeritory(String underTeritory) {
        this.underTeritory = underTeritory;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }
}
