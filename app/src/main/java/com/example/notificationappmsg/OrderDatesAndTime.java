package com.example.notificationappmsg;

public class OrderDatesAndTime {

    private String orderDate,orderTime,OEN_NUMBER_ID,companyId;

    public OrderDatesAndTime(String orderDate, String orderTime, String OEN_NUMBER_ID,String companyId) {
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.OEN_NUMBER_ID = OEN_NUMBER_ID;
        this.companyId=companyId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOEN_NUMBER_ID() {
        return OEN_NUMBER_ID;
    }

    public void setOEN_NUMBER_ID(String OEN_NUMBER_ID) {
        this.OEN_NUMBER_ID = OEN_NUMBER_ID;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
