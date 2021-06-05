package com.example.notificationappmsg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ActivityCricketers extends AppCompatActivity {

    RecyclerView recyclerCricketers;
    ArrayList<Cricketer> cricketersList = new ArrayList<>();
    DatabaseReference dtbase;
    EditText ettph,etstage,etyearofsupply;
    Spinner brandspinner;
    Button btnlist;
    LinearLayout linearLayoutEquip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricketers);

        recyclerCricketers = findViewById(R.id.recycler_cricketers);

        dtbase= FirebaseDatabase.getInstance().getReference("Company_Equipment_details");
        btnlist=findViewById(R.id.submitlist);
        ettph=findViewById(R.id.ettph);
        etstage=findViewById(R.id.etstage);
        etyearofsupply=findViewById(R.id.etyearofsupply);
        brandspinner=findViewById(R.id.brandspinner);
        linearLayoutEquip=findViewById(R.id.addequipment);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerCricketers.setLayoutManager(layoutManager);

        linearLayoutEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityCricketers.this,PlacingOrderPlace.class);
                startActivity(intent);
            }
        });

        cricketersList = (ArrayList<Cricketer>) getIntent().getExtras().getSerializable("list");
        recyclerCricketers.setAdapter(new CricketerAdapter(cricketersList));

        CompanyID idsc=new CompanyID();
        Toast.makeText(ActivityCricketers.this,idsc.getCOMPanyID(),Toast.LENGTH_LONG).show();

        btnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(ettph.getText().toString()) && !TextUtils.isEmpty(etstage.getText().toString()) && !TextUtils.isEmpty(etyearofsupply.getText().toString())) {
                    String tph = ettph.getText().toString();
                    String stage = etstage.getText().toString();
                    String brand = brandspinner.getSelectedItem().toString();
                    String Yearofsupply = etyearofsupply.getText().toString();

                    CompanyID company_ID=new CompanyID();
                    String id=company_ID.getCOMPanyID();


                    SendingLists modellist = new SendingLists(tph, stage, brand, Yearofsupply, cricketersList);
                    dtbase.child(id).setValue(modellist);
                    Toast.makeText(ActivityCricketers.this,"Details Saved",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ActivityCricketers.this,"Fill All Details!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}