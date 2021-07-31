package com.example.notificationappmsg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;


public class CompanyDetailsEntering extends AppCompatActivity {


    EditText companyName,nameOfHead,companyAddress,companyPhoneNumber,companySiteAddress,companyEmail,personName,personDesignation,personPhoneNumber;
    Spinner visitinputquarry,existingPlantOwner,pollutionCleaned,siteaddress,sitestate;
    Button btnvisitinput;
    private DatabaseReference dtrefcompany;
    TextView inputDate,inputTime;
    String santab ="1";
    String santab1 ="2";

    private static final String TAG = "Next Visit";

    private TextView nextvisitdate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public String date;


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
        siteaddress=findViewById(R.id.visitinputdistrict);
        sitestate=findViewById(R.id.visitinputstate);

        dtrefcompany= FirebaseDatabase.getInstance().getReference("CompanyDetails");

        nextvisitdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CompanyDetailsEntering.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "-" + day + "-" + year);

                date = month + "-" + day + "-" + year;
                nextvisitdate.setText(date);
            }
        };




        inputDate.setText(getTodaysDate());
        inputTime.setText(getTimewitham());

        btnvisitinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(companyName.getText().toString())&&!TextUtils.isEmpty(nameOfHead.getText().toString())&&!TextUtils.isEmpty(companyAddress.getText().toString())&&!TextUtils.isEmpty(companyPhoneNumber.getText().toString())&&!TextUtils.isEmpty(companySiteAddress.getText().toString())&&!TextUtils.isEmpty(companyEmail.getText().toString())&&!TextUtils.isEmpty(personName.getText().toString())&&!TextUtils.isEmpty(personDesignation.getText().toString())&&!TextUtils.isEmpty(personPhoneNumber.getText().toString())&&!TextUtils.isEmpty(nextvisitdate.getText().toString())){
                    CreateCompany(companyName.getText().toString(),nameOfHead.getText().toString(),companyAddress.getText().toString(),companyPhoneNumber.getText().toString(),companySiteAddress.getText().toString(),companyEmail.getText().toString(),personName.getText().toString(),personDesignation.getText().toString(),personPhoneNumber.getText().toString(),date,getTodaysDate(),getTimewitham(),siteaddress.getSelectedItem().toString(),sitestate.getSelectedItem().toString());
                }else{
                    Toast.makeText(CompanyDetailsEntering.this,"Enter All Details",Toast.LENGTH_LONG).show();
                }


            }
        });



    }

    private void CreateCompany(String companyName,String nameofhead,String companyAddress,String companyPhoneNumber,String companySiteAddress,String companyEmail,String personName,String personDesignation,String personPhoneNumber,String nextvisitdate,String ApplicationDate,String ApplicationTime,String SiteAddress,String SiteState) {



        final String randomKey= UUID.randomUUID().toString();
        String companyId= randomKey.concat(companyPhoneNumber);
        CompanyDetails companyDetails= new CompanyDetails(companyId,companyName,nameofhead,companyAddress,companyPhoneNumber,companySiteAddress,companyEmail,personName,personDesignation,personPhoneNumber,nextvisitdate,ApplicationDate,ApplicationTime,SiteAddress,SiteState);
        dtrefcompany.child(companyId).setValue(companyDetails);
        Toast.makeText(CompanyDetailsEntering.this,"Details are Uploaded Successfully",Toast.LENGTH_LONG).show();
        FirebaseAuth authUser=FirebaseAuth.getInstance();
        FirebaseUser UserAuth=authUser.getCurrentUser();
        CheckUserAccessLevel7(UserAuth.getUid());
    }

    private String getTodaysDate(){
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }
    private String getTimewitham(){
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }

    private void CheckUserAccessLevel7(String uid) {
        DocumentReference df= FirebaseFirestore.getInstance().collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String levelCHecker=documentSnapshot.getString("UserLevel");

                if(Objects.equals(levelCHecker, santab))
                {
                    Intent intent = new Intent(CompanyDetailsEntering.this,ServiceEngineerDashboard.class);
                    startActivity(intent);
                }
                else if(Objects.equals(levelCHecker, santab1)){
                    Intent intent = new Intent(CompanyDetailsEntering.this, BusinessExecutiveDashboard.class);
                    startActivity(intent);
                }else
                {
                    Intent intent = new Intent(CompanyDetailsEntering.this, TeritoryManagerDashboard.class);
                    startActivity(intent);
                }

            }
        });
    }
}