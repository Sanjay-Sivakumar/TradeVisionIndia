package com.example.notificationappmsg;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class SearchResultDetails {

    private String companyId,OenId,companyName,companyPhone,companyEmail,compayAddress;

    public SearchResultDetails(){

    }

    public SearchResultDetails(String companyId, String oenId, String companyName, String companyPhone, String companyEmail, String compayAddress) {
        this.companyId = companyId;
        this.OenId = oenId;
        this.companyName = companyName;
        this.companyPhone = companyPhone;
        this.companyEmail = companyEmail;
        this.compayAddress = compayAddress;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getOenId() {
        return OenId;
    }

    public void setOenId(String oenId) {
        OenId = oenId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompayAddress() {
        return compayAddress;
    }

    public void setCompayAddress(String compayAddress) {
        this.compayAddress = compayAddress;
    }
}
