package com.example.notificationappmsg;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class LastVisitDetails {

    private String Company_Visit_ID,DateVisited,TimeVisited,visitRemarks;

    public LastVisitDetails() {
    }

    public LastVisitDetails(String company_Visit_ID, String dateVisited, String timeVisited, String visitRemarks) {
        Company_Visit_ID = company_Visit_ID;
        DateVisited = dateVisited;
        TimeVisited = timeVisited;
        this.visitRemarks = visitRemarks;
    }

    public String getCompany_Visit_ID() {
        return Company_Visit_ID;
    }

    public void setCompany_Visit_ID(String company_Visit_ID) {
        Company_Visit_ID = company_Visit_ID;
    }

    public String getDateVisited() {
        return DateVisited;
    }

    public void setDateVisited(String dateVisited) {
        DateVisited = dateVisited;
    }

    public String getTimeVisited() {
        return TimeVisited;
    }

    public void setTimeVisited(String timeVisited) {
        TimeVisited = timeVisited;
    }

    public String getVisitRemarks() {
        return visitRemarks;
    }

    public void setVisitRemarks(String visitRemarks) {
        this.visitRemarks = visitRemarks;
    }
}
