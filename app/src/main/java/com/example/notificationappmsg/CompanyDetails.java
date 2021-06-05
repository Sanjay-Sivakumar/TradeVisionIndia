package com.example.notificationappmsg;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class CompanyDetails {

    String companyName, nameofhead,companyAddress,companyPhoneNumber,companySiteAddress,companyEmail,personName,personDesignation,personPhoneNumber,nextvisitdate,Applicationdate,ApplicationTime;
    String companyId;


    public CompanyDetails(){

    }

    public CompanyDetails(String companyId,String companyName, String nameofhead, String companyAddress, String companyPhoneNumber, String companySiteAddress, String companyEmail, String personName, String personDesignation, String personPhoneNumber, String nextvisitdate,String Applicationdate,String ApplicationTime) {

        this.companyId=companyId;
        this.companyName = companyName;

        this.nameofhead = nameofhead;
        this.companyAddress = companyAddress;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companySiteAddress = companySiteAddress;
        this.companyEmail = companyEmail;
        this.personName = personName;
        this.personDesignation = personDesignation;
        this.personPhoneNumber = personPhoneNumber;
        this.nextvisitdate = nextvisitdate;
        this.Applicationdate=Applicationdate;
        this.ApplicationTime=ApplicationTime;
    }

    public CompanyDetails(String companyId,String companyName,String companyPhoneNumber,String companyAddress,String companyEmail)
    {
        this.companyId=companyId;
        this.companyName=companyName;
        this.companyPhoneNumber=companyPhoneNumber;
        this.companyAddress=companyAddress;
        this.companyEmail=companyEmail;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNameofhead() {
        return nameofhead;
    }

    public void setNameofhead(String nameofhead) {
        this.nameofhead = nameofhead;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public String getCompanySiteAddress() {
        return companySiteAddress;
    }

    public void setCompanySiteAddress(String companySiteAddress) {
        this.companySiteAddress = companySiteAddress;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonDesignation() {
        return personDesignation;
    }

    public void setPersonDesignation(String personDesignation) {
        this.personDesignation = personDesignation;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setPersonPhoneNumber(String personPhoneNumber) {
        this.personPhoneNumber = personPhoneNumber;
    }

    public String getNextvisitdate() {
        return nextvisitdate;
    }

    public void setNextvisitdate(String nextvisitdate) {
        this.nextvisitdate = nextvisitdate;
    }

    public String getApplicationdate() {
        return Applicationdate;
    }

    public void setApplicationdate(String applicationdate) {
        Applicationdate = applicationdate;
    }

    public String getApplicationTime() {
        return ApplicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        ApplicationTime = applicationTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
