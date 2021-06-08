package com.example.notificationappmsg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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

public class ServiceEntry extends AppCompatActivity {


    ListView listViewServiceList;
    ListView listViewServicemodel;
    ArrayList<ServiceEntryGathering> serviceEntryDetailList;
    DatabaseReference databaseServiceList;
    ArrayList<String> equipmentListService;


    public String viewOen1,viewCname1,viewCphone1,viewCemail1,viewCaddress1;

    TextView cnamepr2,cphonepr2,cemailpr2,caddresspr2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_entry);

        databaseServiceList = FirebaseDatabase.getInstance().getReference("Service_Details");
        listViewServiceList = (ListView) findViewById(R.id.listViewservicelist);
        listViewServicemodel=findViewById(R.id.listViewservieequipments);

        serviceEntryDetailList = new ArrayList<>();
        equipmentListService= new ArrayList<>();

        CompanyID companyID=new CompanyID();
        viewOen1=CompanyID.getCOMPany_INS();
        viewCname1=CompanyID.getComPany_Name();
        viewCemail1=CompanyID.getComPany_Email_id();
        viewCaddress1=CompanyID.getComPany_Address();
        viewCphone1=CompanyID.getComPany_phone_number();

        cnamepr2=findViewById(R.id.textViewcompanynameprint1);
        cphonepr2=findViewById(R.id.textViewcompanyphoneprint1);
        cemailpr2=findViewById(R.id.textViewCompanyemailprint1);
        caddresspr2=findViewById(R.id.textViewCompanyaddressprint1);

        cnamepr2.setText(viewCname1);
        cphonepr2.setText(viewCphone1);
        cemailpr2.setText(viewCemail1);
        caddresspr2.setText(viewCaddress1);


    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        Query query=databaseServiceList.orderByChild("inspection_NO").equalTo(viewOen1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                serviceEntryDetailList.clear();
                equipmentListService.clear();
                ArrayAdapter adapter1= new ArrayAdapter<>(ServiceEntry.this,android.R.layout.simple_list_item_1, equipmentListService);
                listViewServicemodel.setAdapter(adapter1);

                //iterating through all the nodes
              for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    ServiceEntryGathering busno = postSnapshot.getValue(ServiceEntryGathering.class);
                    //adding artist to the list
                    serviceEntryDetailList.add(busno);
                    String oen=busno.getInspection_NO();


                    for (DataSnapshot ds : dataSnapshot.child(viewOen1).child("serviceList").getChildren()) {

                        if (ds.exists()) {
                           Cricketer crket = ds.getValue(Cricketer.class);

                           String modelname=crket.getModelName();
                            String modelnumber=crket.getModelNumber();
                            equipmentListService.add(modelname);
                            equipmentListService.add(modelnumber);


                        } else {
                            Toast.makeText(ServiceEntry.this, "Snapshot not found ", Toast.LENGTH_LONG).show();
                        }

                    }
                    adapter1.notifyDataSetChanged();
                }

                //creating adapter
                ServiceEntryDetailsList BustimeAdapter = new ServiceEntryDetailsList(ServiceEntry.this, serviceEntryDetailList);
                //attaching adapter to the listview
                listViewServiceList.setAdapter(BustimeAdapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}