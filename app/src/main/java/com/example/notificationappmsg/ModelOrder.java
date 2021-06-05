package com.example.notificationappmsg;

public class ModelOrder {

    private String OrderPhotoID,OrderPhotoLink;

    public ModelOrder(String orderPhotoID, String orderPhotoLink) {
        OrderPhotoID = orderPhotoID;
        OrderPhotoLink = orderPhotoLink;
    }

    public String getOrderPhotoID() {
        return OrderPhotoID;
    }

    public void setOrderPhotoID(String orderPhotoID) {
        OrderPhotoID = orderPhotoID;
    }

    public String getOrderPhotoLink() {
        return OrderPhotoLink;
    }

    public void setOrderPhotoLink(String orderPhotoLink) {
        OrderPhotoLink = orderPhotoLink;
    }
}
