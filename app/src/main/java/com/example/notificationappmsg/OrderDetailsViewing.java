package com.example.notificationappmsg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OrderDetailsViewing extends AppCompatActivity {



    ListView listViewOrderList;
    ListView listViewOrdermodel;
    ArrayList<OrderEntryDetails> orderEntryDetailList;
    DatabaseReference databaseOrderList;
    ArrayList<String> cricketers;

    public String viewOen,viewCname,viewCphone,viewCemail,viewCaddress,viewcompId;

    TextView cnamepr1,cphonepr1,cemailpr1,caddresspr1;

    LinearLayout linearLayoutpayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_viewing);

        CompanyID companyID=new CompanyID();
        viewOen=CompanyID.getCOMPany_OEN();
        viewCname=CompanyID.getComPany_Name();
        viewCemail=CompanyID.getComPany_Email_id();
        viewCaddress=CompanyID.getComPany_Address();
        viewCphone=CompanyID.getComPany_phone_number();
        viewcompId=companyID.getCOMPanyID();






        databaseOrderList = FirebaseDatabase.getInstance().getReference("Order_Details");
        listViewOrderList = (ListView) findViewById(R.id.listViewordersview);
        listViewOrdermodel=findViewById(R.id.listVieworderlistview);
        cnamepr1=findViewById(R.id.textViewcompanynameprint);
        cphonepr1=findViewById(R.id.textViewcompanyphoneprint);
        cemailpr1=findViewById(R.id.textViewCompanyemailprint);
        caddresspr1=findViewById(R.id.textViewCompanyaddressprint);
        linearLayoutpayment=findViewById(R.id.gotopayment);

        cnamepr1.setText(viewCname);
        cphonepr1.setText(viewCphone);
        cemailpr1.setText(viewCemail);
        caddresspr1.setText(viewCaddress);


        orderEntryDetailList = new ArrayList<>();
        cricketers=new ArrayList<>();

        linearLayoutpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderDetailsViewing.this,PaymentDetailsViewing.class);
                intent.putExtra("RequestOen",viewOen);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        Query query=databaseOrderList.orderByChild("oen_ID").equalTo(viewOen);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                orderEntryDetailList.clear();
                cricketers.clear();

                ArrayAdapter<String> adapter=new ArrayAdapter<String>(OrderDetailsViewing.this,android.R.layout.simple_list_item_1,cricketers);
                listViewOrdermodel.setAdapter(adapter);


               for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                   //getting artist
                   OrderEntryDetails busno = postSnapshot.getValue(OrderEntryDetails.class);
                   //adding artist to the list
                   orderEntryDetailList.add(busno);
                   String oen=busno.getOEN_ID();



                   for (DataSnapshot ds : dataSnapshot.child(viewOen+""+viewcompId).child("listEqiupments").getChildren()) {

                       if (ds.exists()) {
                           Cricketer crket = ds.getValue(Cricketer.class);

                           String modelname=crket.getModelName();
                           String modelnumber=crket.getModelNumber();
                           cricketers.add(modelname);
                           cricketers.add(modelnumber);

                       } else {
                           Toast.makeText(OrderDetailsViewing.this, "Snapshot not found ", Toast.LENGTH_LONG).show();
                       }

                   }


                   adapter.notifyDataSetChanged();
               }

                //creating adapter
                OrderEntryDetailsList BustimeAdapter = new OrderEntryDetailsList(OrderDetailsViewing.this, orderEntryDetailList);
                //attaching adapter to the listview
                listViewOrderList.setAdapter(BustimeAdapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}