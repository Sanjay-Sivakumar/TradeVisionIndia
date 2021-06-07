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



public class PaymentDetailsList extends ArrayAdapter<paymentmodel1> {
    private Activity context;
    List<paymentmodel1> paymentmodel1s;

    public PaymentDetailsList(Activity context, List<paymentmodel1> paymentmodel1s) {
        super(context, R.layout.layout_payment_list, paymentmodel1s);
        this.context = context;
        this.paymentmodel1s = paymentmodel1s;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View listViewItem = inflater.inflate(R.layout.layout_payment_list, null, true);

        TextView textViewPaymentOenid = listViewItem.findViewById(R.id.textViewoenidpayment);
        TextView textViewPaymentNetPrice =  listViewItem.findViewById(R.id.textViewnetpricepayment);
        TextView textViewPaymentAmount = listViewItem.findViewById(R.id.textViewamountpaidpayment);
        TextView textViewPaymentChequeDetails =listViewItem.findViewById(R.id.textViewchequedetailspayment);
        TextView textViewPaymentdoneDate = listViewItem.findViewById(R.id.textViewpaymentdate);
        TextView textViewPaymentdoneTime =  listViewItem.findViewById(R.id.textViewpaymenttime);




        paymentmodel1 appointmentpatient = paymentmodel1s.get(position);
        textViewPaymentOenid.setText(appointmentpatient.getOEN_ID());
        textViewPaymentNetPrice.setText(appointmentpatient.getNETPRICE());
        textViewPaymentAmount.setText(appointmentpatient.getAmountPaid());
        textViewPaymentChequeDetails.setText(appointmentpatient.getChequeDetails());
        textViewPaymentdoneDate.setText(appointmentpatient.getORderDate());
        textViewPaymentdoneTime.setText(appointmentpatient.getORderTime());

        return listViewItem;
    }
}
