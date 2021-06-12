package com.example.notificationappmsg;
import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class LastVisitDates {
    private String Dates_Company_ID,DateLast,TimeLast;

    public LastVisitDates() {
    }

    public LastVisitDates(String dates_Company_ID, String dateLast, String timeLast) {
        Dates_Company_ID = dates_Company_ID;
        DateLast = dateLast;
        TimeLast = timeLast;
    }

    public String getDates_Company_ID() {
        return Dates_Company_ID;
    }

    public void setDates_Company_ID(String dates_Company_ID) {
        Dates_Company_ID = dates_Company_ID;
    }

    public String getDateLast() {
        return DateLast;
    }

    public void setDateLast(String dateLast) {
        DateLast = dateLast;
    }

    public String getTimeLast() {
        return TimeLast;
    }

    public void setTimeLast(String timeLast) {
        TimeLast = timeLast;
    }
}
