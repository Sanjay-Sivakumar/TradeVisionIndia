package com.example.notificationappmsg;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class OrderLossDetails {



    private String OrderLoss_Comp_ID,OrderLoss_oen,OrderLoss_CompanyName,OrderLoss_CompanyPhone,OrderLoss_CompanyEmail,OrderLoss_Address,CompetitorsBrand,CompetitiorsPrice,LossPrice,LossScope,Exisiting_Brand,OtherReason,OrderLossDate,OrderLossTime;

    public OrderLossDetails(){

    }

    public OrderLossDetails(String orderLoss_Comp_ID, String orderLoss_oen, String orderLoss_CompanyName, String orderLoss_CompanyPhone, String orderLoss_CompanyEmail, String orderLoss_Address, String competitorsBrand, String competitiorsPrice, String lossPrice, String lossScope, String exisiting_Brand, String otherReason, String orderLossDate, String orderLossTime) {
        OrderLoss_Comp_ID = orderLoss_Comp_ID;
        OrderLoss_oen = orderLoss_oen;
        OrderLoss_CompanyName = orderLoss_CompanyName;
        OrderLoss_CompanyPhone = orderLoss_CompanyPhone;
        OrderLoss_CompanyEmail = orderLoss_CompanyEmail;
        OrderLoss_Address = orderLoss_Address;
        CompetitorsBrand = competitorsBrand;
        CompetitiorsPrice = competitiorsPrice;
        LossPrice = lossPrice;
        LossScope = lossScope;
        Exisiting_Brand = exisiting_Brand;
        OtherReason = otherReason;
        OrderLossDate = orderLossDate;
        OrderLossTime = orderLossTime;
    }

    public String getOrderLoss_Comp_ID() {
        return OrderLoss_Comp_ID;
    }

    public void setOrderLoss_Comp_ID(String orderLoss_Comp_ID) {
        OrderLoss_Comp_ID = orderLoss_Comp_ID;
    }

    public String getOrderLoss_oen() {
        return OrderLoss_oen;
    }

    public void setOrderLoss_oen(String orderLoss_oen) {
        OrderLoss_oen = orderLoss_oen;
    }

    public String getOrderLoss_CompanyName() {
        return OrderLoss_CompanyName;
    }

    public void setOrderLoss_CompanyName(String orderLoss_CompanyName) {
        OrderLoss_CompanyName = orderLoss_CompanyName;
    }

    public String getOrderLoss_CompanyPhone() {
        return OrderLoss_CompanyPhone;
    }

    public void setOrderLoss_CompanyPhone(String orderLoss_CompanyPhone) {
        OrderLoss_CompanyPhone = orderLoss_CompanyPhone;
    }

    public String getOrderLoss_CompanyEmail() {
        return OrderLoss_CompanyEmail;
    }

    public void setOrderLoss_CompanyEmail(String orderLoss_CompanyEmail) {
        OrderLoss_CompanyEmail = orderLoss_CompanyEmail;
    }

    public String getOrderLoss_Address() {
        return OrderLoss_Address;
    }

    public void setOrderLoss_Address(String orderLoss_Address) {
        OrderLoss_Address = orderLoss_Address;
    }

    public String getCompetitorsBrand() {
        return CompetitorsBrand;
    }

    public void setCompetitorsBrand(String competitorsBrand) {
        CompetitorsBrand = competitorsBrand;
    }

    public String getCompetitiorsPrice() {
        return CompetitiorsPrice;
    }

    public void setCompetitiorsPrice(String competitiorsPrice) {
        CompetitiorsPrice = competitiorsPrice;
    }

    public String getLossPrice() {
        return LossPrice;
    }

    public void setLossPrice(String lossPrice) {
        LossPrice = lossPrice;
    }

    public String getLossScope() {
        return LossScope;
    }

    public void setLossScope(String lossScope) {
        LossScope = lossScope;
    }

    public String getExisiting_Brand() {
        return Exisiting_Brand;
    }

    public void setExisiting_Brand(String exisiting_Brand) {
        Exisiting_Brand = exisiting_Brand;
    }

    public String getOtherReason() {
        return OtherReason;
    }

    public void setOtherReason(String otherReason) {
        OtherReason = otherReason;
    }

    public String getOrderLossDate() {
        return OrderLossDate;
    }

    public void setOrderLossDate(String orderLossDate) {
        OrderLossDate = orderLossDate;
    }

    public String getOrderLossTime() {
        return OrderLossTime;
    }

    public void setOrderLossTime(String orderLossTime) {
        OrderLossTime = orderLossTime;
    }
}
