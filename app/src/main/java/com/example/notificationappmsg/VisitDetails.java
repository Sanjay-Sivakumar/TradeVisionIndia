package com.example.notificationappmsg;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class VisitDetails {

    private String VisitedDate,VisitedTime,meetingPerson,tph,stage,valueOfOffer,visitRemarks,accompainedPerson,visitDetails,PurposeOfVisit,NextVisitDate,Visit_Company_Id,UserNames,UId;
    private ArrayList<Cricketer> listEqiupments;
    public VisitDetails() {
    }

    public VisitDetails(String visitedDate, String visitedTime, String meetingPerson, String tph, String stage, String valueOfOffer, String visitRemarks, String accompainedPerson, String visitDetails, String purposeOfVisit, String nextVisitDate, String companyId,ArrayList<Cricketer> liststring,String UId,String UserNames) {
        VisitedDate = visitedDate;
        VisitedTime = visitedTime;
        this.meetingPerson = meetingPerson;
        this.tph = tph;
        this.stage = stage;
        this.valueOfOffer = valueOfOffer;
        this.visitRemarks = visitRemarks;
        this.accompainedPerson = accompainedPerson;
        this.visitDetails = visitDetails;
        this.PurposeOfVisit = purposeOfVisit;
        this.NextVisitDate = nextVisitDate;
        this.Visit_Company_Id = companyId;
        this.listEqiupments=liststring;
        this.UserNames=UserNames;

    }

    public String getVisitedDate() {
        return VisitedDate;
    }

    public void setVisitedDate(String visitedDate) {
        VisitedDate = visitedDate;
    }

    public String getVisitedTime() {
        return VisitedTime;
    }

    public void setVisitedTime(String visitedTime) {
        VisitedTime = visitedTime;
    }

    public String getMeetingPerson() {
        return meetingPerson;
    }

    public void setMeetingPerson(String meetingPerson) {
        this.meetingPerson = meetingPerson;
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

    public String getValueOfOffer() {
        return valueOfOffer;
    }

    public void setValueOfOffer(String valueOfOffer) {
        this.valueOfOffer = valueOfOffer;
    }

    public String getVisitRemarks() {
        return visitRemarks;
    }

    public void setVisitRemarks(String visitRemarks) {
        this.visitRemarks = visitRemarks;
    }

    public String getAccompainedPerson() {
        return accompainedPerson;
    }

    public void setAccompainedPerson(String accompainedPerson) {
        this.accompainedPerson = accompainedPerson;
    }

    public String getVisitDetails() {
        return visitDetails;
    }

    public void setVisitDetails(String visitDetails) {
        this.visitDetails = visitDetails;
    }

    public String getPurposeOfVisit() {
        return PurposeOfVisit;
    }

    public void setPurposeOfVisit(String purposeOfVisit) {
        PurposeOfVisit = purposeOfVisit;
    }

    public String getNextVisitDate() {
        return NextVisitDate;
    }

    public void setNextVisitDate(String nextVisitDate) {
        NextVisitDate = nextVisitDate;
    }

    public String getVisit_Company_Id() {
        return Visit_Company_Id;
    }

    public void setVisit_Company_Id(String visit_Company_Id) {
        Visit_Company_Id = visit_Company_Id;
    }

    public ArrayList<Cricketer> getListEqiupments() {
        return listEqiupments;
    }

    public void setListEqiupments(ArrayList<Cricketer> listEqiupments) {
        this.listEqiupments = listEqiupments;
    }

    public String getUserNames() {
        return UserNames;
    }

    public void setUserNames(String userNames) {
        UserNames = userNames;
    }

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }
}
