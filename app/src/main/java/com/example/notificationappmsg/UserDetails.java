package com.example.notificationappmsg;

public class UserDetails {

    private  static String insid;

    public UserDetails(){

    }
    public UserDetails(String id)
    {
        insid=id;
    }


    public static String getInsid() {
        return insid;
    }

    public static void setInsid(String insid) {
        UserDetails.insid = insid;
    }
}
