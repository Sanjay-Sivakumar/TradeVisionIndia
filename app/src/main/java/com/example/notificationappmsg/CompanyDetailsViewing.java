package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CompanyDetailsViewing extends AppCompatActivity {

    ListView listViewCompanyDetails;
    ArrayList<CompanyDetails> companyDetails;
    DatabaseReference databaseCompanyDetails;

    ArrayList<String> myarrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details_viewing);


        databaseCompanyDetails = FirebaseDatabase.getInstance().getReference("CompanyDetails");
        listViewCompanyDetails = findViewById(R.id.listViewcompany);

        companyDetails = new ArrayList<>();



    }
    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseCompanyDetails.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                companyDetails.clear();

                if(dataSnapshot.exists()) {

                    Toast.makeText(CompanyDetailsViewing.this,"Snapshot found",Toast.LENGTH_LONG).show();


                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        //getting artist
                        CompanyDetails busno = postSnapshot.getValue(CompanyDetails.class);
                        //adding artist to the list
                        companyDetails.add(busno);
                    }
                    CompanyDetailsList CompanyAdapter = new CompanyDetailsList(CompanyDetailsViewing.this, companyDetails);
                    //attaching adapter to the listview
                    listViewCompanyDetails.setAdapter(CompanyAdapter);
                }else{
                    Toast.makeText(CompanyDetailsViewing.this,"Snapshot Not found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}