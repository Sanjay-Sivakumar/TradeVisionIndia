package com.example.notificationappmsg;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class OrderLossDetailsList extends ArrayAdapter<OrderLossDetails> {
    private Activity context;
    List<OrderLossDetails> orderLossDetails;

    public OrderLossDetailsList(Activity context, List<OrderLossDetails> orderLossDetails) {
        super(context, R.layout.layout_orderloss_list, orderLossDetails);
        this.context = context;
        this.orderLossDetails = orderLossDetails;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View listViewItem = inflater.inflate(R.layout.layout_orderloss_list, null, true);

        TextView textViewOrderlossoid = listViewItem.findViewById(R.id.textViewoenidloss);
        TextView textViewOrderlosscname =  listViewItem.findViewById(R.id.textViewcompanynameloss);
        TextView textViewOrderlosscphone = listViewItem.findViewById(R.id.textViewcompanyphonenloss);
        TextView textViewOrderlosscEmail =listViewItem.findViewById(R.id.textViewcompanyemailloss);
        TextView textViewcompPrice = listViewItem.findViewById(R.id.textViewpriceloss);
        TextView textViewcompBrand =  listViewItem.findViewById(R.id.textViewbrandloss);
        TextView textViewExistingBrand =  listViewItem.findViewById(R.id.textViewexitingbrandloss);
        TextView textViewprice =  listViewItem.findViewById(R.id.textViewlossprice);
        TextView textViewScope = listViewItem.findViewById(R.id.textViewlossscope);
        TextView textViewOtherReason =  listViewItem.findViewById(R.id.textViewotherReason);
        TextView textViewOrderLossdate =  listViewItem.findViewById(R.id.textVieworderlossdate);
        TextView textViewOrderLosstime = listViewItem.findViewById(R.id.textViewlosstime);



        OrderLossDetails appointmentpatient = orderLossDetails.get(position);
        textViewOrderlossoid.setText(appointmentpatient.getOrderLoss_oen());
        textViewOrderlosscname.setText(appointmentpatient.getOrderLoss_CompanyName());
        textViewOrderlosscphone.setText(appointmentpatient.getOrderLoss_CompanyPhone());
        textViewOrderlosscEmail.setText(appointmentpatient.getOrderLoss_CompanyEmail());
        textViewcompPrice.setText(appointmentpatient.getCompetitiorsPrice());
        textViewcompBrand.setText(appointmentpatient.getCompetitorsBrand());
        textViewExistingBrand.setText(appointmentpatient.getExisiting_Brand());
        textViewprice.setText(appointmentpatient.getLossPrice());
        textViewScope.setText(appointmentpatient.getLossScope());
        textViewOtherReason.setText(appointmentpatient.getOtherReason());
        textViewOrderLossdate.setText(appointmentpatient.getOrderLossDate());
        textViewOrderLosstime.setText(appointmentpatient.getOrderLossTime());

        return listViewItem;
    }
}
