package com.example.notificationappmsg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class CompanyDetailsEntering extends AppCompatActivity {


    EditText companyName,nameOfHead,companyAddress,companyPhoneNumber,companySiteAddress,companyEmail,personName,personDesignation,personPhoneNumber,nextvisitdate;
    Spinner visitinputquarry,existingPlantOwner,pollutionCleaned;
    Button btnvisitinput;
    private DatabaseReference dtrefcompany;
    TextView inputDate,inputTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details_entering);

        companyName=findViewById(R.id.visitinputcompanyname);
        nameOfHead=findViewById(R.id.visitinputnameofthehead);
        companyAddress=findViewById(R.id.visitinputcompanyaddress);
        companyPhoneNumber=findViewById(R.id.visitinputcompanyphonenumber);
        companySiteAddress=findViewById(R.id.visitinputcompanysiteaddress);
        companyEmail=findViewById(R.id.visitinputcompanyemail);
        personName=findViewById(R.id.visitinputpersonname);
        personDesignation=findViewById(R.id.visitinputpersondesignation);
        personPhoneNumber=findViewById(R.id.visitinputpersonphonenumber);
        visitinputquarry=findViewById(R.id.visitinputcompanyquarry);
        existingPlantOwner=findViewById(R.id.visitinputcompanyexistingplantowner);
        pollutionCleaned=findViewById(R.id.visitinputpollution);
        btnvisitinput=findViewById(R.id.visitinputbutton);
        nextvisitdate=findViewById(R.id.nextvisitdate);
        inputDate=findViewById(R.id.visitinputdate);
        inputTime=findViewById(R.id.visitinputtime);

        dtrefcompany= FirebaseDatabase.getInstance().getReference("CompanyDetails");


        inputDate.setText(getTodaysDate());
        inputTime.setText(getTimewitham());

        btnvisitinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(companyName.getText().toString())&&!TextUtils.isEmpty(nameOfHead.getText().toString())&&!TextUtils.isEmpty(companyAddress.getText().toString())&&!TextUtils.isEmpty(companyPhoneNumber.getText().toString())&&!TextUtils.isEmpty(companySiteAddress.getText().toString())&&!TextUtils.isEmpty(companyEmail.getText().toString())&&!TextUtils.isEmpty(personName.getText().toString())&&!TextUtils.isEmpty(personDesignation.getText().toString())&&!TextUtils.isEmpty(personPhoneNumber.getText().toString())&&!TextUtils.isEmpty(nextvisitdate.getText().toString())){
                    CreateCompany(companyName.getText().toString(),nameOfHead.getText().toString(),companyAddress.getText().toString(),companyPhoneNumber.getText().toString(),companySiteAddress.getText().toString(),companyEmail.getText().toString(),personName.getText().toString(),personDesignation.getText().toString(),personPhoneNumber.getText().toString(),nextvisitdate.getText().toString(),getTodaysDate(),getTimewitham());
                }else{
                    Toast.makeText(CompanyDetailsEntering.this,"Enter All Details",Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private void CreateCompany(String companyName,String nameofhead,String companyAddress,String companyPhoneNumber,String companySiteAddress,String companyEmail,String personName,String personDesignation,String personPhoneNumber,String nextvisitdate,String ApplicationDate,String ApplicationTime) {



        final String randomKey= UUID.randomUUID().toString();
        String companyId= randomKey.concat(companyPhoneNumber);
        CompanyDetails companyDetails= new CompanyDetails(companyId,companyName,nameofhead,companyAddress,companyPhoneNumber,companySiteAddress,companyEmail,personName,personDesignation,personPhoneNumber,nextvisitdate,ApplicationDate,ApplicationTime);
        dtrefcompany.child(companyId).setValue(companyDetails);
        Toast.makeText(CompanyDetailsEntering.this,"Details are filled",Toast.LENGTH_LONG).show();
       Intent intent=new Intent(CompanyDetailsEntering.this,PlacingOrderPlace.class);
        startActivity(intent);
    }

    private String getTodaysDate(){
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }
    private String getTimewitham(){
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }
}