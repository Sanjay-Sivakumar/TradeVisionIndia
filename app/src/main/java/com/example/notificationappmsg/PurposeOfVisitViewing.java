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

public class PurposeOfVisitViewing extends AppCompatActivity {

    ListView listViewvisitdetails;
    ListView listViewvisitequipment;
    ArrayList<VisitDetails> visitDetails;
    DatabaseReference databaseVisitList;
    ArrayList<String> cricketers3;

    public String visitCname,visitCphone,visitCemail,visitCaddress,visitcompId;

    TextView cnamepr3,cphonepr3,cemailpr3,caddresspr3;

    LinearLayout LinearLayoutOfferCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purpose_of_visit_viewing);

        CompanyID companyID=new CompanyID();
        visitCname=CompanyID.getComPany_Name();
        visitCemail=CompanyID.getComPany_Email_id();
        visitCaddress=CompanyID.getComPany_Address();
        visitCphone=CompanyID.getComPany_phone_number();
        visitcompId=companyID.getCOMPanyID();

        databaseVisitList = FirebaseDatabase.getInstance().getReference("Visit_Details");
        listViewvisitdetails = (ListView) findViewById(R.id.listViewofferview);
        listViewvisitequipment=findViewById(R.id.listViewvisitequip);
        LinearLayoutOfferCopy=findViewById(R.id.lastOfferCopy);
        cnamepr3=findViewById(R.id.textViewcompanynameprint3);
        cphonepr3=findViewById(R.id.textViewcompanyphoneprint3);
        cemailpr3=findViewById(R.id.textViewCompanyemailprint3);
        caddresspr3=findViewById(R.id.textViewCompanyaddressprint3);


        cnamepr3.setText(visitCname);
        cphonepr3.setText(visitCphone);
        cemailpr3.setText(visitCemail);
        caddresspr3.setText(visitCaddress);


        visitDetails = new ArrayList<>();
        cricketers3=new ArrayList<>();



    }
    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        Query query=databaseVisitList.orderByChild("companyId").equalTo(visitcompId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                visitDetails.clear();
                cricketers3.clear();

                ArrayAdapter<String> adapter=new ArrayAdapter<String>(PurposeOfVisitViewing.this,android.R.layout.simple_list_item_1,cricketers3);
                listViewvisitequipment.setAdapter(adapter);


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    VisitDetails busno = postSnapshot.getValue(VisitDetails.class);
                    //adding artist to the list
                    visitDetails.add(busno);
                    String dates=busno.getVisitedDate();
                    String time=busno.getVisitedTime();



                    for (DataSnapshot ds : dataSnapshot.child(visitcompId+""+dates+""+time).child("listEqiupments").getChildren()) {

                        if (ds.exists()) {
                            Cricketer crket = ds.getValue(Cricketer.class);

                            String modelname=crket.getModelName();
                            String modelnumber=crket.getModelNumber();
                            cricketers3.add(modelname);
                            cricketers3.add(modelnumber);

                        } else {
                            Toast.makeText(PurposeOfVisitViewing.this, "Snapshot not found ", Toast.LENGTH_LONG).show();
                        }

                    }


                    adapter.notifyDataSetChanged();
                }

                //creating adapter
                VisitEntryDetailsList BustimeAdapter = new VisitEntryDetailsList(PurposeOfVisitViewing.this, visitDetails);
                //attaching adapter to the listview
                listViewvisitdetails.setAdapter(BustimeAdapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}