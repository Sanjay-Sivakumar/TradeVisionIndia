package com.example.notificationappmsg;

public class model
{
    String namedb,phnodb,emaildb,purldb,WorkZone1,UnderTeritory1;

    public model(){

    }

    public model(String namedb,String phnodb,String emaildb,String purldb)
    {
        this.namedb=namedb;
        this.emaildb=emaildb;
        this.phnodb=phnodb;
        this.purldb=purldb;
    }

    public model(String namedb, String phnodb, String emaildb, String purldb,String WorkZone1,String UnderTeritory1) {
        this.namedb = namedb;
        this.phnodb = phnodb;
        this.emaildb = emaildb;
        this.purldb = purldb;
        this.WorkZone1=WorkZone1;
        this.UnderTeritory1=UnderTeritory1;
    }

    public String getNamedb() {
        return namedb;
    }

    public String getPhnodb() {
        return phnodb;
    }


    public String getEmaildb() {
        return emaildb;
    }


    public String getPurldb() {
        return purldb;
    }

    public void setNamedb(String namedb) {
        this.namedb = namedb;
    }

    public void setPhnodb(String phnodb) {
        this.phnodb = phnodb;
    }

    public void setEmaildb(String emaildb) {
        this.emaildb = emaildb;
    }

    public void setPurldb(String purldb) {
        this.purldb = purldb;
    }

    public String getWorkZone1() {
        return WorkZone1;
    }

    public void setWorkZone1(String workZone1) {
        WorkZone1 = workZone1;
    }

    public String getUnderTeritory1() {
        return UnderTeritory1;
    }

    public void setUnderTeritory1(String underTeritory1) {
        UnderTeritory1 = underTeritory1;
    }
}
