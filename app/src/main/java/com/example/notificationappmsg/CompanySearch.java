package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CompanySearch extends AppCompatActivity {

    DatabaseReference companyRef;
    private ListView listViewcom;
    private AutoCompleteTextView txtsearch;
    String COMPANYID;

    public String RequestCodeCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_search);
        companyRef= FirebaseDatabase.getInstance().getReference("CompanyDetails");
        listViewcom=(ListView)findViewById(R.id.companysearchlist);
        txtsearch=(AutoCompleteTextView)findViewById(R.id.companysearch);



        RequestCodeCompany= getIntent().getStringExtra("RequestCode");


        ValueEventListener event=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                populateSearch(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(CompanySearch.this,"Some Issues are found Try Again Later!",Toast.LENGTH_LONG).show();
            }
        };
        companyRef.addListenerForSingleValueEvent(event);

    }

    private void populateSearch(DataSnapshot snapshot) {
        ArrayList<String> names=new ArrayList<>();
        if(snapshot.exists())
        {

            for(DataSnapshot ds:snapshot.getChildren())
            {
                String Fname=ds.child("companyName").getValue(String.class);
                names.add(Fname);
            }
            ArrayAdapter Aadapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,names);
            txtsearch.setAdapter(Aadapter);
            txtsearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String fname=txtsearch.getText().toString();
                    SearchCompany(fname);
                }
            });

        }else
        {
            Toast.makeText(CompanySearch.this,"Company is not found",Toast.LENGTH_LONG).show();
        }
    }

    private void SearchCompany(String fname) {

        Query query=companyRef.orderByChild("companyName").equalTo(fname);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    ArrayList<String> listtuser=new ArrayList<>();
                    for(DataSnapshot ds:snapshot.getChildren())
                    {
                        CompanyDetails models= new CompanyDetails(ds.child("companyId").getValue(String.class),ds.child("companyName").getValue(String.class),ds.child("companyPhoneNumber").getValue(String.class),ds.child("companyAddress").getValue(String.class),ds.child("companyEmail").getValue(String.class));
                        listtuser.add(models.getCompanyName()+"\n"+models.getCompanyPhoneNumber()+"\n"+models.getCompanyAddress()+"\n"+models.getCompanyEmail());
                        COMPANYID=models.getCompanyId();
                        CompanyID company_ID=new CompanyID();
                        company_ID.setCOMPanyID(models.getCompanyId());
                        CompanyID.setComPany_Email_id(models.getCompanyEmail());
                        CompanyID.setComPany_Address(models.getCompanyAddress());
                        CompanyID.setComPany_Name(models.getCompanyName());

                    }

                    ArrayAdapter AAdapter=new ArrayAdapter(CompanySearch.this, android.R.layout.simple_list_item_1,listtuser);
                    listViewcom.setAdapter(AAdapter);
                    listViewcom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            GotoIntent();
                        }
                    });


                }else{
                    Toast.makeText(CompanySearch.this,"No Company Found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void GotoIntent() {

        if(RequestCodeCompany.equals("3"))
        {
            Intent intent=new Intent(CompanySearch.this,PlacingOrderPlace.class);
            intent.putExtra("RequestCode","2");
            startActivity(intent);

        }else if(RequestCodeCompany.equals("2"))
        {
            Intent intent=new Intent(CompanySearch.this,PlacingOrderPlace.class);
            intent.putExtra("RequestCode","1");
            startActivity(intent);
        }else{
            Intent intent=new Intent(CompanySearch.this,CompanyDetailsViewing.class);
            startActivity(intent);
        }


    }

}