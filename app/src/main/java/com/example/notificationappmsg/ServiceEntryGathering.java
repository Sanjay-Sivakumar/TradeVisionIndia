package com.example.notificationappmsg;

import com.google.firebase.database.IgnoreExtraProperties;
import java.util.ArrayList;

@IgnoreExtraProperties
public class ServiceEntryGathering {

    private String hourValue,oilValue,greaseValue,underWarranty,InStock,specific_Complaint,currentParts_Supplier,EngineerObservation,ServiceDate,ServiceTime,partsWeared,operatorName,operatorPhoneNumber,afterServiceReport,serviceAmountCollected,AccopaniedBy,customer_Feedback,Service_OnCall,Service_comp_Id,Inspection_NO;
    ArrayList<Cricketer>serviceList;

    public ServiceEntryGathering(){

    }

    public ServiceEntryGathering(String hourValue, String oilValue, String greaseValue, String underWarranty, String inStock, String specific_Complaint, String currentParts_Supplier, String engineerObservation, String serviceDate, String serviceTime, String partsWeared, String operatorName, String operatorPhoneNumber, String afterServiceReport, String serviceAmountCollected, String accopaniedBy, String customer_Feedback, String service_OnCall, String service_comp_Id, String inspection_NO,ArrayList<Cricketer> serviceList) {
        this.hourValue = hourValue;
        this.oilValue = oilValue;
        this.greaseValue = greaseValue;
        this.underWarranty = underWarranty;
        InStock = inStock;
        this.specific_Complaint = specific_Complaint;
        this.currentParts_Supplier = currentParts_Supplier;
        EngineerObservation = engineerObservation;
        ServiceDate = serviceDate;
        ServiceTime = serviceTime;
        this.partsWeared = partsWeared;
        this.operatorName = operatorName;
        this.operatorPhoneNumber = operatorPhoneNumber;
        this.afterServiceReport = afterServiceReport;
        this.serviceAmountCollected = serviceAmountCollected;
        AccopaniedBy = accopaniedBy;
        this.customer_Feedback = customer_Feedback;
        this.Service_OnCall = service_OnCall;
        Service_comp_Id = service_comp_Id;
        Inspection_NO = inspection_NO;
        this.serviceList = serviceList;
    }

    public String getHourValue() {
        return hourValue;
    }

    public void setHourValue(String hourValue) {
        this.hourValue = hourValue;
    }

    public String getOilValue() {
        return oilValue;
    }

    public void setOilValue(String oilValue) {
        this.oilValue = oilValue;
    }

    public String getGreaseValue() {
        return greaseValue;
    }

    public void setGreaseValue(String greaseValue) {
        this.greaseValue = greaseValue;
    }

    public String getUnderWarranty() {
        return underWarranty;
    }

    public void setUnderWarranty(String underWarranty) {
        this.underWarranty = underWarranty;
    }

    public String getInStock() {
        return InStock;
    }

    public void setInStock(String inStock) {
        InStock = inStock;
    }

    public String getSpecific_Complaint() {
        return specific_Complaint;
    }

    public void setSpecific_Complaint(String specific_Complaint) {
        this.specific_Complaint = specific_Complaint;
    }

    public String getCurrentParts_Supplier() {
        return currentParts_Supplier;
    }

    public void setCurrentParts_Supplier(String currentParts_Supplier) {
        this.currentParts_Supplier = currentParts_Supplier;
    }

    public String getEngineerObservation() {
        return EngineerObservation;
    }

    public void setEngineerObservation(String engineerObservation) {
        EngineerObservation = engineerObservation;
    }

    public String getServiceDate() {
        return ServiceDate;
    }

    public void setServiceDate(String serviceDate) {
        ServiceDate = serviceDate;
    }

    public String getServiceTime() {
        return ServiceTime;
    }

    public void setServiceTime(String serviceTime) {
        ServiceTime = serviceTime;
    }

    public String getPartsWeared() {
        return partsWeared;
    }

    public void setPartsWeared(String partsWeared) {
        this.partsWeared = partsWeared;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorPhoneNumber() {
        return operatorPhoneNumber;
    }

    public void setOperatorPhoneNumber(String operatorPhoneNumber) {
        this.operatorPhoneNumber = operatorPhoneNumber;
    }

    public String getAfterServiceReport() {
        return afterServiceReport;
    }

    public void setAfterServiceReport(String afterServiceReport) {
        this.afterServiceReport = afterServiceReport;
    }

    public String getServiceAmountCollected() {
        return serviceAmountCollected;
    }

    public void setServiceAmountCollected(String serviceAmountCollected) {
        this.serviceAmountCollected = serviceAmountCollected;
    }

    public String getAccopaniedBy() {
        return AccopaniedBy;
    }

    public void setAccopaniedBy(String accopaniedBy) {
        AccopaniedBy = accopaniedBy;
    }

    public String getCustomer_Feedback() {
        return customer_Feedback;
    }

    public void setCustomer_Feedback(String customer_Feedback) {
        this.customer_Feedback = customer_Feedback;
    }

    public String getService_OnCall() {
        return Service_OnCall;
    }

    public void setService_OnCall(String service_OnCall) {
        Service_OnCall = service_OnCall;
    }

    public String getService_comp_Id() {
        return Service_comp_Id;
    }

    public void setService_comp_Id(String service_comp_Id) {
        Service_comp_Id = service_comp_Id;
    }

    public String getInspection_NO() {
        return Inspection_NO;
    }

    public void setInspection_NO(String inspection_NO) {
        Inspection_NO = inspection_NO;
    }

    public ArrayList<Cricketer> getServiceList() {
        return serviceList;
    }

    public void setServiceList(ArrayList<Cricketer> serviceList) {
        this.serviceList = serviceList;
    }
}
