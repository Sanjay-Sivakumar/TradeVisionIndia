package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceSearchEngine extends AppCompatActivity {


    AutoCompleteTextView servicesearchtext;
    ListView serviceSearchResults;
    DatabaseReference ServicesearchEngineRef;
    ToggleButton toggleButtonServiceSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_search_engine);

        servicesearchtext=findViewById(R.id.servicesearchenginetext);
        toggleButtonServiceSearch=findViewById(R.id.toggleButtonsearchservice);
        serviceSearchResults=findViewById(R.id.listofservicesearchresults);


        ServicesearchEngineRef= FirebaseDatabase.getInstance().getReference("Search_Engine_Details_service");

        CallValueEventListenerForName();

        toggleButtonServiceSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    CallValueEventListenerForIns();
                }
                else{
                    CallValueEventListenerForName();
                }
            }
        });

    }

    private void CallValueEventListenerForIns() {

        ValueEventListener event=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Toast.makeText(ServiceSearchEngine.this,"Search now by Inspection Number",Toast.LENGTH_LONG).show();
                PopulateSearchByIns(snapshot);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ServicesearchEngineRef.addListenerForSingleValueEvent(event);

    }





    private void CallValueEventListenerForName() {
        ValueEventListener event=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PopulateSearchByName(snapshot);
                Toast.makeText(ServiceSearchEngine.this,"Search Now!",Toast.LENGTH_LONG).show();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ServicesearchEngineRef.addListenerForSingleValueEvent(event);

    }


    private void PopulateSearchByIns(DataSnapshot snapshot) {
        ArrayList<String> names=new ArrayList<>();
        if(snapshot.exists())
        {
            for(DataSnapshot ds:snapshot.getChildren())
            {
                String Fname=ds.child("service_id_Ins").getValue(String.class);
                names.add(Fname);
            }
            ArrayAdapter Aadapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,names);
            servicesearchtext.setAdapter(Aadapter);
            servicesearchtext.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String fname=servicesearchtext.getText().toString();
                   SearchCompanyByIns(fname);
                }
            });

        }else
        {
            Toast.makeText(ServiceSearchEngine.this,"Company is not found",Toast.LENGTH_LONG).show();
        }
    }

    private void SearchCompanyByIns(String fname) {

        Query query=ServicesearchEngineRef.orderByChild("service_id_Ins").equalTo(fname);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    ArrayList<String> listtuser=new ArrayList<>();
                    for(DataSnapshot ds:snapshot.getChildren())
                    {
                        SearchServiceDetails models= new SearchServiceDetails(ds.child("service_company_id").getValue(String.class),ds.child("service_id_Ins").getValue(String.class),ds.child("service_company_name").getValue(String.class),ds.child("service_company_phone").getValue(String.class),ds.child("service_company_email").getValue(String.class),ds.child("service_company_address").getValue(String.class));
                        listtuser.add(models.getService_id_Ins()+"\n"+models.getService_company_name()+"\n"+models.getService_company_phone()+"\n"+models.getService_company_address()+"\n"+models.getService_company_email());

                        CompanyID company_ID=new CompanyID();
                        company_ID.setCOMPanyID(models.getService_company_id());

                    }

                    ArrayAdapter AAdapter=new ArrayAdapter(ServiceSearchEngine.this, android.R.layout.simple_list_item_1,listtuser);
                    serviceSearchResults.setAdapter(AAdapter);
                    serviceSearchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //Toast.makeText(UserSearch.this,"you clicked something",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ServiceSearchEngine.this,PaymentReceipt.class);
                            intent.putExtra("RequestCode", "1");
                            startActivity(intent);
                        }
                    });


                }else{
                    Toast.makeText(ServiceSearchEngine.this,"No Company Found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void PopulateSearchByName(DataSnapshot snapshot) {
        ArrayList<String> names=new ArrayList<>();
        if(snapshot.exists())
        {
            for(DataSnapshot ds:snapshot.getChildren())
            {
                String Fname=ds.child("service_company_name").getValue(String.class);
                names.add(Fname);
            }
            ArrayAdapter Aadapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,names);
            servicesearchtext.setAdapter(Aadapter);
            servicesearchtext.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String fname=servicesearchtext.getText().toString();
                    SearchCompanyByName(fname);
                }
            });

        }else
        {
            Toast.makeText(ServiceSearchEngine.this,"Company is not found ",Toast.LENGTH_LONG).show();
        }
    }

    private void SearchCompanyByName(String fname) {

        Query query=ServicesearchEngineRef.orderByChild("service_company_name").equalTo(fname);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    ArrayList<String> listtuser=new ArrayList<>();
                    for(DataSnapshot ds:snapshot.getChildren())
                    {
                        SearchServiceDetails models= new SearchServiceDetails(ds.child("service_company_id").getValue(String.class),ds.child("service_id_Ins").getValue(String.class),ds.child("service_company_name").getValue(String.class),ds.child("service_company_phone").getValue(String.class),ds.child("service_company_email").getValue(String.class),ds.child("service_company_address").getValue(String.class));
                        listtuser.add(models.getService_id_Ins()+"\n"+models.getService_company_name()+"\n"+models.getService_company_phone()+"\n"+models.getService_company_address()+"\n"+models.getService_company_email());

                        CompanyID company_ID=new CompanyID();
                        company_ID.setCOMPanyID(models.getService_company_id());
                    }

                    ArrayAdapter AAdapter=new ArrayAdapter(ServiceSearchEngine.this, android.R.layout.simple_list_item_1,listtuser);
                    serviceSearchResults.setAdapter(AAdapter);
                    serviceSearchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //Toast.makeText(UserSearch.this,"you clicked something",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ServiceSearchEngine.this,PaymentReceipt.class);
                            intent.putExtra("RequestCode", "1");
                            startActivity(intent);
                        }
                    });


                }else{
                    Toast.makeText(ServiceSearchEngine.this,"No Company Found ",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}