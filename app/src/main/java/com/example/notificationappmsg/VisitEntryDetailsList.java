package com.example.notificationappmsg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;



public class VisitEntryDetailsList extends ArrayAdapter<VisitDetails> {
    private Activity context;
    List<VisitDetails> visitDetails;

    public VisitEntryDetailsList(Activity context, List<VisitDetails> visitDetails) {
        super(context, R.layout.layout_visitentry_list, visitDetails);
        this.context = context;
        this.visitDetails = visitDetails;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View listViewItem = inflater.inflate(R.layout.layout_visitentry_list, null, true);

        TextView textViewVisitDetails = listViewItem.findViewById(R.id.textViewvistdetail);
        TextView textViewPurposeOfVisit =  listViewItem.findViewById(R.id.textViewpurposeofvisit);
        TextView textViewVisitStage = listViewItem.findViewById(R.id.textViewvisitstage);
        TextView textViewVisitTph =listViewItem.findViewById(R.id.textViewvisittph);
        TextView textViewMeetingPerson = listViewItem.findViewById(R.id.textViewmeetingperson);
        TextView textViewValueOfOffer =  listViewItem.findViewById(R.id.textViewvalueofoffer);
        TextView textViewVisitRemarks =  listViewItem.findViewById(R.id.textViewVisitremarks);
        TextView textViewVisitAccompainedBy =  listViewItem.findViewById(R.id.textViewaccompainedperson);
        TextView textViewLastvisitdate = listViewItem.findViewById(R.id.textViewlastvisitdate);
        TextView textViewLastVisitTime =  listViewItem.findViewById(R.id.textViewlastvisittime);




        VisitDetails appointmentpatient = visitDetails.get(position);
        textViewVisitDetails.setText(appointmentpatient.getVisitDetails());
        textViewPurposeOfVisit.setText(appointmentpatient.getPurposeOfVisit());
        textViewVisitStage.setText(appointmentpatient.getStage());
        textViewVisitTph.setText(appointmentpatient.getTph());
        textViewMeetingPerson.setText(appointmentpatient.getMeetingPerson());
        textViewValueOfOffer.setText(appointmentpatient.getValueOfOffer());
        textViewVisitAccompainedBy.setText(appointmentpatient.getAccompainedPerson());
        textViewVisitRemarks.setText(appointmentpatient.getVisitRemarks());
        textViewLastvisitdate.setText(appointmentpatient.getVisitedDate());
        textViewLastVisitTime.setText(appointmentpatient.getVisitedTime());


        return listViewItem;
    }
}