package com.example.notificationappmsg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class CompanyDetailsList extends ArrayAdapter<CompanyDetails> {
    private Activity context;
    List<CompanyDetails> companyDetails;

    public CompanyDetailsList(Activity context, List<CompanyDetails> companyDetails) {
        super(context, R.layout.layout_companydetails_list, companyDetails);
        this.context = context;
        this.companyDetails = companyDetails;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View listViewItem = inflater.inflate(R.layout.layout_companydetails_list, null, true);

        TextView textViewCompanyname = listViewItem.findViewById(R.id.textViewcompanyname);
        TextView textViewPhonenumber =  listViewItem.findViewById(R.id.textViewcompanyphonenumber);
        TextView textViewCompanyemail = listViewItem.findViewById(R.id.textViewcompanyemail);
        TextView textViewCompanyaddress =listViewItem.findViewById(R.id.textViewcompanyaddresss);
        TextView textViewSiteAddress = listViewItem.findViewById(R.id.textViewsiteaddress);
        TextView textViewNameofhead =  listViewItem.findViewById(R.id.textViewnameofthehead);
        TextView textViewPersonName =  listViewItem.findViewById(R.id.textViewpersonname);
        TextView textViewPersonPhoneNumber =  listViewItem.findViewById(R.id.textViewpersonphonenumber);
        TextView textViewPersonDesignation = listViewItem.findViewById(R.id.textViewpersondesignation);
        TextView textViewAppliedDate =  listViewItem.findViewById(R.id.textViewapplieddate);
        TextView textViewNextVisit =  listViewItem.findViewById(R.id.textViewnextvisit);


        CompanyDetails appointmentpatient = companyDetails.get(position);
        textViewCompanyname.setText(appointmentpatient.getCompanyName());
        textViewPhonenumber.setText(appointmentpatient.getCompanyPhoneNumber());
        textViewCompanyemail.setText(appointmentpatient.getCompanyEmail());
        textViewCompanyaddress.setText(appointmentpatient.getCompanyAddress());
        textViewSiteAddress.setText(appointmentpatient.getCompanySiteAddress());
        textViewNameofhead.setText(appointmentpatient.getNameofhead());
        textViewPersonName.setText(appointmentpatient.getPersonName());
        textViewPersonPhoneNumber.setText(appointmentpatient.getPersonPhoneNumber());
        textViewPersonDesignation.setText(appointmentpatient.getPersonDesignation());
        textViewAppliedDate.setText(appointmentpatient.getApplicationdate());
        textViewNextVisit.setText(appointmentpatient.getNextvisitdate());



        return listViewItem;
    }
}

