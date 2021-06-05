package com.example.notificationappmsg;

import com.google.firebase.database.IgnoreExtraProperties;
import java.util.ArrayList;

@IgnoreExtraProperties
public class OrderEntryDetails {

    private String Order_TPH;
    private String Order_Brand;
    private String Order_Stage;
    private String Order_Washing;
    private String Basic_Price;
    private String Transportation_Price;
    private String TPC_Name;
    private String TPC_Amount;
    private String Advance_Pay;
    private String cheque_RTGS_deatis;
    private String Order_Remarks;
    private String OEN_ID;
    private String Ordered_Date;
    private String Ordered_Time;
    private String NET_Price;
    private String tcsamount;
    private String gstamount;
    private String Total_Price;
    private String Company_ID;
    ArrayList<Cricketer> listEqiupments;



    public OrderEntryDetails(String orderTPH,String Order_Brand, String orderStage, String orderWashing, String TCPName, String TCPAmount, String cheque_RTGS_deatis, String order_Remarks, String orderedDate, String orderedTime,String basicPrice,String gstamount,String transportationPrice,String advance_Pay,String totalPrice,String nETPrice,String tcsamount,String id,String OEN_ID,ArrayList<Cricketer> liststring) {
        this.Order_TPH = orderTPH;
        this.Order_Brand=Order_Brand;
        this.Order_Stage = orderStage;
        this.Order_Washing = orderWashing;
        this.TPC_Name = TCPName;
        this.TPC_Amount = TCPAmount;
        this.cheque_RTGS_deatis = cheque_RTGS_deatis;
        Order_Remarks = order_Remarks;
        this.Ordered_Date = orderedDate;
        this.Ordered_Time = orderedTime;
        this.Basic_Price=basicPrice;
        this.gstamount=gstamount;
        this.Total_Price=totalPrice;
        this.Advance_Pay=advance_Pay;
        this.NET_Price=nETPrice;
        this.Transportation_Price=transportationPrice;
        this.Company_ID=id;
        this.OEN_ID=OEN_ID;
        this.listEqiupments = liststring;
        this.tcsamount=tcsamount;

    }

    public OrderEntryDetails(){

    }

    public String getOrder_TPH() {
        return Order_TPH;
    }

    public void setOrder_TPH(String order_TPH) {
        Order_TPH = order_TPH;
    }

    public String getOrder_Stage() {
        return Order_Stage;
    }

    public void setOrder_Stage(String order_Stage) {
        Order_Stage = order_Stage;
    }

    public String getOrder_Washing() {
        return Order_Washing;
    }

    public void setOrder_Washing(String order_Washing) {
        Order_Washing = order_Washing;
    }

    public String getBasic_Price() {
        return Basic_Price;
    }

    public void setBasic_Price(String basic_Price) {
        Basic_Price = basic_Price;
    }

    public String getTransportation_Price() {
        return Transportation_Price;
    }

    public void setTransportation_Price(String transportation_Price) {
        Transportation_Price = transportation_Price;
    }

    public String getTPC_Name() {
        return TPC_Name;
    }

    public void setTPC_Name(String TPC_Name) {
        this.TPC_Name = TPC_Name;
    }

    public String getTPC_Amount() {
        return TPC_Amount;
    }

    public void setTPC_Amount(String TPC_Amount) {
        this.TPC_Amount = TPC_Amount;
    }

    public String getAdvance_Pay() {
        return Advance_Pay;
    }

    public void setAdvance_Pay(String advance_Pay) {
        Advance_Pay = advance_Pay;
    }

    public String getCheque_RTGS_deatis() {
        return cheque_RTGS_deatis;
    }

    public void setCheque_RTGS_deatis(String cheque_RTGS_deatis) {
        this.cheque_RTGS_deatis = cheque_RTGS_deatis;
    }

    public String getOrder_Remarks() {
        return Order_Remarks;
    }

    public void setOrder_Remarks(String order_Remarks) {
        Order_Remarks = order_Remarks;
    }

    public String getOEN_ID() {
        return OEN_ID;
    }

    public void setOEN_ID(String OEN_ID) {
        this.OEN_ID = OEN_ID;
    }

    public String getOrdered_Date() {
        return Ordered_Date;
    }

    public void setOrdered_Date(String ordered_Date) {
        Ordered_Date = ordered_Date;
    }

    public String getOrdered_Time() {
        return Ordered_Time;
    }

    public void setOrdered_Time(String ordered_Time) {
        Ordered_Time = ordered_Time;
    }

    public String getNET_Price() {
        return NET_Price;
    }

    public void setNET_Price(String NET_Price) {
        this.NET_Price = NET_Price;
    }

    public String getTcsamount() {
        return tcsamount;
    }

    public void setTcsamount(String tcsamount) {
        this.tcsamount = tcsamount;
    }

    public String getGstamount() {
        return gstamount;
    }

    public void setGstamount(String gstamount) {
        this.gstamount = gstamount;
    }

    public String getTotal_Price() {
        return Total_Price;
    }

    public void setTotal_Price(String total_Price) {
        Total_Price = total_Price;
    }

    public String getCompany_ID() {
        return Company_ID;
    }

    public void setCompany_ID(String company_ID) {
        Company_ID = company_ID;
    }

    public ArrayList<Cricketer> getListEqiupments() {
        return listEqiupments;
    }

    public void setListEqiupments(ArrayList<Cricketer> listEqiupments) {
        this.listEqiupments = listEqiupments;
    }

    public String getOrder_Brand() {
        return Order_Brand;
    }

    public void setOrder_Brand(String order_Brand) {
        Order_Brand = order_Brand;
    }
}
