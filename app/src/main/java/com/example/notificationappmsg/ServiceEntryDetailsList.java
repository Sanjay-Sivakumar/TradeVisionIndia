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


public class ServiceEntryDetailsList extends ArrayAdapter<ServiceEntryGathering> {
    private Activity context;
    List<ServiceEntryGathering> serviceEntryGatherings;

    public ServiceEntryDetailsList(Activity context, List<ServiceEntryGathering> serviceEntryGatherings) {
        super(context, R.layout.layout_service_list, serviceEntryGatherings);
        this.context = context;
        this.serviceEntryGatherings = serviceEntryGatherings;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View listViewItem = inflater.inflate(R.layout.layout_service_list, null, true);

        TextView textViewInspectionNo = listViewItem.findViewById(R.id.textViewinsno);
        TextView textViewHour =  listViewItem.findViewById(R.id.textViewhour);
        TextView textViewOil = listViewItem.findViewById(R.id.textViewoil);
        TextView textViewGrease =listViewItem.findViewById(R.id.textViewgrease);
        TextView textViewUnderWarranty = listViewItem.findViewById(R.id.textViewunderwarranty);
        TextView textViewPartsWeared =  listViewItem.findViewById(R.id.textViewpartsweared);
        TextView textViewCurrentPartsSupplier =  listViewItem.findViewById(R.id.textViewcurrentparts);
        TextView textViewEngineerObservation =  listViewItem.findViewById(R.id.textViewengineerobservation);
        TextView textViewServiceReport = listViewItem.findViewById(R.id.textViewserviceReport);
        TextView textViewAmountCollected =  listViewItem.findViewById(R.id.textViewamountcollected);
        TextView textViewServiceCall =  listViewItem.findViewById(R.id.textViewservicecall);
        TextView textViewSpecificComplaint = listViewItem.findViewById(R.id.textViewspecificcomplaint);
        TextView textViewAccopainedBy =  listViewItem.findViewById(R.id.textViewaccopainedby);
        TextView textViewOperatorName =  listViewItem.findViewById(R.id.textViewoperatorname);
        TextView textViewOperatorPhoneNumber =  listViewItem.findViewById(R.id.textViewoperatorphone);
        TextView textViewServiceDate =  listViewItem.findViewById(R.id.textViewservicedate);
        TextView textViewServiceTime =  listViewItem.findViewById(R.id.textViewservicetime);



        ServiceEntryGathering appointmentpatient= serviceEntryGatherings.get(position);
        textViewInspectionNo.setText(appointmentpatient.getInspection_NO());
        textViewHour.setText(appointmentpatient.getHourValue());
        textViewOil.setText(appointmentpatient.getOilValue());
        textViewGrease.setText(appointmentpatient.getGreaseValue());
        textViewUnderWarranty.setText(appointmentpatient.getUnderWarranty());
        textViewPartsWeared.setText(appointmentpatient.getPartsWeared());
        textViewCurrentPartsSupplier.setText(appointmentpatient.getCurrentParts_Supplier());
        textViewEngineerObservation.setText(appointmentpatient.getEngineerObservation());
        textViewServiceReport.setText(appointmentpatient.getAfterServiceReport());
        textViewAccopainedBy.setText(appointmentpatient.getAccopaniedBy());
        textViewAmountCollected.setText(appointmentpatient.getServiceAmountCollected());
        textViewServiceCall.setText(appointmentpatient.getService_OnCall());
        textViewSpecificComplaint.setText(appointmentpatient.getSpecific_Complaint());
        textViewOperatorName.setText(appointmentpatient.getOperatorName());
        textViewOperatorPhoneNumber.setText(appointmentpatient.getOperatorPhoneNumber());
        textViewServiceDate.setText(appointmentpatient.getServiceDate());
        textViewServiceTime.setText(appointmentpatient.getServiceTime());


        return listViewItem;
    }
}
