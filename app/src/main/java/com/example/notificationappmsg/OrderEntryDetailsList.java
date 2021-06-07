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


public class OrderEntryDetailsList extends ArrayAdapter<OrderEntryDetails> {
    private Activity context;
    List<OrderEntryDetails> orderEntryDetails;

    public OrderEntryDetailsList(Activity context, List<OrderEntryDetails> orderEntryDetails) {
        super(context, R.layout.layout_orderentry_list, orderEntryDetails);
        this.context = context;
        this.orderEntryDetails = orderEntryDetails;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View listViewItem = inflater.inflate(R.layout.layout_orderentry_list, null, true);

        TextView textViewOrderTph = listViewItem.findViewById(R.id.textViewordertph);
        TextView textViewOrderStage =  listViewItem.findViewById(R.id.textVieworderstage);
        TextView textViewOrderWashing = listViewItem.findViewById(R.id.textViewwashing);
        TextView textViewOrderTpcName =listViewItem.findViewById(R.id.textViewtpcname);
        TextView textViewOrderTpcAmount = listViewItem.findViewById(R.id.textViewtpcamount);
        TextView textViewBasicPrice =  listViewItem.findViewById(R.id.textViewbasicprice);
        TextView textViewTransportationPrice =  listViewItem.findViewById(R.id.textViewtransportprice);
        TextView textViewGSTAmount =  listViewItem.findViewById(R.id.textViewgstamount);
        TextView textViewTcsAmount = listViewItem.findViewById(R.id.textViewtcsamountview);
        TextView textViewNetPrice =  listViewItem.findViewById(R.id.textViewnetpriceview);
        TextView textViewTotalPrice =  listViewItem.findViewById(R.id.textViewtotalprice);
        TextView textViewChequeDetails = listViewItem.findViewById(R.id.textViewchequedetails);
        TextView textViewOrderRemarks =  listViewItem.findViewById(R.id.textVieworderremarks);
        TextView textViewOrdereddate =  listViewItem.findViewById(R.id.textViewordereddate);
        TextView textViewOrderedtime =  listViewItem.findViewById(R.id.textViewordertime);
        TextView textViewOrderednumber =  listViewItem.findViewById(R.id.textViewoenid);
        TextView textViewOrderAdvancePay=listViewItem.findViewById(R.id.textViewadvancepay);



        OrderEntryDetails appointmentpatient = orderEntryDetails.get(position);
        textViewOrderTph.setText(appointmentpatient.getOrder_TPH());
        textViewOrderStage.setText(appointmentpatient.getOrder_Stage());
        textViewOrderWashing.setText(appointmentpatient.getOrder_Washing());
        textViewOrderTpcName.setText(appointmentpatient.getTPC_Name());
        textViewOrderTpcAmount.setText(appointmentpatient.getTPC_Amount());
        textViewBasicPrice.setText(appointmentpatient.getBasic_Price());
        textViewTransportationPrice.setText(appointmentpatient.getTransportation_Price());
        textViewGSTAmount.setText(appointmentpatient.getGstamount());
        textViewTcsAmount.setText(appointmentpatient.getTcsamount());
        textViewNetPrice.setText(appointmentpatient.getNET_Price());
        textViewTotalPrice.setText(appointmentpatient.getTotal_Price());
        textViewChequeDetails.setText(appointmentpatient.getCheque_RTGS_deatis());
        textViewOrderRemarks.setText(appointmentpatient.getOrder_Remarks());
        textViewOrdereddate.setText(appointmentpatient.getOrdered_Date());
        textViewOrderedtime.setText(appointmentpatient.getOrdered_Time());
        textViewOrderednumber.setText(appointmentpatient.getOEN_ID());
        textViewOrderAdvancePay.setText(appointmentpatient.getAdvance_Pay());


        return listViewItem;
    }
}

