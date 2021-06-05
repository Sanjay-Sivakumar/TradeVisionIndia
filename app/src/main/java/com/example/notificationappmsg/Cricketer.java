package com.example.notificationappmsg;

import java.io.Serializable;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Cricketer implements Serializable {

    public String modelNumber;
    public String modelName;

    public Cricketer() {

    }

    public Cricketer(String modelNumber, String modelName) {
        this.modelNumber = modelNumber;
        this.modelName = modelName;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
