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
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchEngine extends AppCompatActivity {


    AutoCompleteTextView searchtext;
    ListView searchResults;
    DatabaseReference searchEngineRef;
    ToggleButton toggleButtonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_engine);

        searchtext=findViewById(R.id.searchenginetext);
        toggleButtonSearch=findViewById(R.id.toggleButtonsearch);
        searchResults=findViewById(R.id.listofsearchresults);


        searchEngineRef= FirebaseDatabase.getInstance().getReference("Search_Engine_Details");

        CallValueEventListenerForName();

        toggleButtonSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    CallValueEventListenerForOen();
                }
                else{


                    CallValueEventListenerForName();
                }
            }
        });

    }

    private void CallValueEventListenerForOen() {

        ValueEventListener event=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Toast.makeText(SearchEngine.this,"Search Now",Toast.LENGTH_LONG).show();
                PopulateSearchByOen(snapshot);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        searchEngineRef.addListenerForSingleValueEvent(event);

    }





    private void CallValueEventListenerForName() {
        ValueEventListener event=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PopulateSearchByName(snapshot);
                Toast.makeText(SearchEngine.this,"Search Now",Toast.LENGTH_LONG).show();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        searchEngineRef.addListenerForSingleValueEvent(event);

        }


    private void PopulateSearchByOen(DataSnapshot snapshot) {
        ArrayList<String> names=new ArrayList<>();
        if(snapshot.exists())
        {
            for(DataSnapshot ds:snapshot.getChildren())
            {
                String Fname=ds.child("oenId").getValue(String.class);
                names.add(Fname);
            }
            ArrayAdapter Aadapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,names);
            searchtext.setAdapter(Aadapter);
            searchtext.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String fname=searchtext.getText().toString();
                    SearchCompanyByOen(fname);
                }
            });

        }else
        {
            Toast.makeText(SearchEngine.this,"Company is not found",Toast.LENGTH_LONG).show();
        }
    }

    private void SearchCompanyByOen(String fname) {

        Query query=searchEngineRef.orderByChild("oenId").equalTo(fname);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    ArrayList<String> listtuser=new ArrayList<>();
                    for(DataSnapshot ds:snapshot.getChildren())
                    {
                        SearchResultDetails models= new SearchResultDetails(ds.child("companyId").getValue(String.class),ds.child("oenId").getValue(String.class),ds.child("companyName").getValue(String.class),ds.child("companyPhoneNumber").getValue(String.class),ds.child("companyEmail").getValue(String.class),ds.child("companyAddress").getValue(String.class));
                        listtuser.add(models.getOenId()+"\n"+models.getCompanyName()+"\n"+models.getCompanyPhone()+"\n"+models.getCompayAddress()+"\n"+models.getCompanyEmail());

                        CompanyID company_ID=new CompanyID();
                        company_ID.setCOMPanyID(models.getCompanyId());

                    }

                    ArrayAdapter AAdapter=new ArrayAdapter(SearchEngine.this, android.R.layout.simple_list_item_1,listtuser);
                    searchResults.setAdapter(AAdapter);
                    searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //Toast.makeText(UserSearch.this,"you clicked something",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SearchEngine.this,PaymentReceipt.class);
                            intent.putExtra("RequestCode", "1");
                            startActivity(intent);
                        }
                    });


                }else{
                    Toast.makeText(SearchEngine.this,"No Company Found",Toast.LENGTH_LONG).show();
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
                String Fname=ds.child("companyName").getValue(String.class);
                names.add(Fname);
            }
            ArrayAdapter Aadapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,names);
            searchtext.setAdapter(Aadapter);
            searchtext.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String fname=searchtext.getText().toString();
                    SearchCompanyByName(fname);
                }
            });

        }else
        {
            Toast.makeText(SearchEngine.this,"Company is not found",Toast.LENGTH_LONG).show();
        }
    }

    private void SearchCompanyByName(String fname) {

        Query query=searchEngineRef.orderByChild("companyName").equalTo(fname);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    ArrayList<String> listtuser=new ArrayList<>();
                    for(DataSnapshot ds:snapshot.getChildren())
                    {
                        SearchResultDetails models= new SearchResultDetails(ds.child("companyId").getValue(String.class),ds.child("oenId").getValue(String.class),ds.child("companyName").getValue(String.class),ds.child("companyPhoneNumber").getValue(String.class),ds.child("companyEmail").getValue(String.class),ds.child("companyAddress").getValue(String.class));
                        listtuser.add(models.getOenId()+"\n"+models.getCompanyName()+"\n"+models.getCompanyPhone()+"\n"+models.getCompayAddress()+"\n"+models.getCompanyEmail());

                        CompanyID company_ID=new CompanyID();
                        company_ID.setCOMPanyID(models.getCompanyId());

                    }

                    ArrayAdapter AAdapter=new ArrayAdapter(SearchEngine.this, android.R.layout.simple_list_item_1,listtuser);
                    searchResults.setAdapter(AAdapter);
                    searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //Toast.makeText(UserSearch.this,"you clicked something",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SearchEngine.this,PaymentReceipt.class);
                            intent.putExtra("RequestCode", "1");
                            startActivity(intent);
                        }
                    });


                }else{
                    Toast.makeText(SearchEngine.this,"No Company Found !!!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
