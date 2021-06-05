package com.example.notificationappmsg;

public class paymentmodel1 {

    private String company_ID,OEN_ID,NETPRICE,ORderDate,ORderTime,AmountPaid,ChequeDetails;

    public paymentmodel1(String company_ID, String OEN_ID, String NETPRICE) {
        this.company_ID = company_ID;
        this.OEN_ID = OEN_ID;
        this.NETPRICE = NETPRICE;
    }

    public paymentmodel1(String company_ID,String OEN_ID,String NETPRICE,String AmountPaid,String chequeDetails,String orderdate,String ordertime)
    {
        this.company_ID=company_ID;
        this.OEN_ID=OEN_ID;
        this.NETPRICE=NETPRICE;
        this.ORderDate=orderdate;
        this.ORderTime=ordertime;
        this.AmountPaid=AmountPaid;
        this.ChequeDetails=chequeDetails;
    }

   /* public paymentmodel1(String company_ID, String OEN_ID, String NETPRICE, String amountPaid, String chequeDetails) {
        this.company_ID = company_ID;
        this.OEN_ID = OEN_ID;
        this.NETPRICE = NETPRICE;
        this.AmountPaid = amountPaid;
        this.ChequeDetails = chequeDetails;
    }*/

    public String getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(String company_ID) {
        this.company_ID = company_ID;
    }

    public String getOEN_ID() {
        return OEN_ID;
    }

    public void setOEN_ID(String OEN_ID) {
        this.OEN_ID = OEN_ID;
    }

    public String getNETPRICE() {
        return NETPRICE;
    }

    public void setNETPRICE(String NETPRICE) {
        this.NETPRICE = NETPRICE;
    }

    public String getORderDate() {
        return ORderDate;
    }

    public void setORderDate(String ORderDate) {
        this.ORderDate = ORderDate;
    }

    public String getORderTime() {
        return ORderTime;
    }

    public void setORderTime(String ORderTime) {
        this.ORderTime = ORderTime;
    }

    public String getAmountPaid() {
        return AmountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        AmountPaid = amountPaid;
    }

    public String getChequeDetails() {
        return ChequeDetails;
    }

    public void setChequeDetails(String chequeDetails) {
        ChequeDetails = chequeDetails;
    }
}
