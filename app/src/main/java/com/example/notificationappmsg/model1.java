package com.example.notificationappmsg;

public class model1
{
    String namedb,phnodb,emaildb,purldb;

    public model1(){

    }

    public model1(String namedb, String phnodb, String emaildb, String purldb) {
        this.namedb = namedb;
        this.phnodb = phnodb;
        this.emaildb = emaildb;
        this.purldb = purldb;
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

}
