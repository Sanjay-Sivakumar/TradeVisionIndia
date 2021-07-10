package com.example.notificationappmsg;

public class model
{
    String namedb,phnodb,emaildb,purldb;

    public model(){

    }

    public model(String namedb, String phnodb, String emaildb, String purldb) {
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
