package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServiceEntryDetails extends AppCompatActivity {


    TextView serviceDate,inspectionno,lastvisithourvalue,lastvisitoilvalue,lastvisitgreasevalue;

    EditText currentvisithourvalue,currentvisitoilvalue,currentvisitgreasevalue,specificComplaint,engineerobservation,partsweared,operatorname;
    EditText operatorphonenumber,currentpartsupplier,afterservicereport,serviceamountcollected,accompainedby,customerfeedback;

    Spinner underwarranty,instock;

    ToggleButton servicecall;

    RecyclerView recyclerCricketers2;
    ArrayList<Cricketer> cricketersList2 = new ArrayList<>();

    DatabaseReference dtbaseservice,dtgetservice,dtbaselastvisitvalue,dtservicecompdetails,dtsearchservice;

    Button submitbtnservice;

    public int flagservice=0;
    public String InspectionNO,Service_company_Id,Service_OnCall,inspection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_entry_details);

        serviceDate=findViewById(R.id.servicedate);
        inspectionno=findViewById(R.id.inspectionnumber);
        lastvisitoilvalue=findViewById(R.id.lastvalueoil);
        lastvisithourvalue=findViewById(R.id.lastvaluehourmeter);
        lastvisitgreasevalue=findViewById(R.id.lastvaluegrease);

        currentvisithourvalue=findViewById(R.id.hourmetervalue);
        currentvisitoilvalue=findViewById(R.id.textViewoilvalue);
        currentvisitgreasevalue=findViewById(R.id.textViewgreasevalue);
        specificComplaint=findViewById(R.id.et_specifycomplaint);
        engineerobservation=findViewById(R.id.et_engineerObservation);
        partsweared=findViewById(R.id.et_partsweared);
        operatorname=findViewById(R.id.serviceoperatorname);

        operatorphonenumber=findViewById(R.id.operatorphonenumber);
        currentpartsupplier=findViewById(R.id.et_currentparts);
        afterservicereport=findViewById(R.id.et_afterservicereport);
        serviceamountcollected=findViewById(R.id.serviceamountcollected);
        accompainedby=findViewById(R.id.accompainedbyperson);
        customerfeedback=findViewById(R.id.et_customerfeedback);
        underwarranty=findViewById(R.id.underwarranty);
        instock=findViewById(R.id.spininstack);

        submitbtnservice=findViewById(R.id.submitservicereport);
        servicecall=findViewById(R.id.toggleButtonservice);

        recyclerCricketers2=findViewById(R.id.recycler_cricketers_service);


        dtbaseservice= FirebaseDatabase.getInstance().getReference("Service_Details");
        dtgetservice = FirebaseDatabase.getInstance().getReference("Service_Details");

        dtbaselastvisitvalue=FirebaseDatabase.getInstance().getReference("Service_LastVisit_Value");
        dtservicecompdetails=FirebaseDatabase.getInstance().getReference("CompanyDetails");
        dtsearchservice=FirebaseDatabase.getInstance().getReference("Search_Engine_Details_service");

        servicecall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    Service_OnCall="General";
                }
                else{


                    Service_OnCall="On Call";
                }
            }
        });

        getEnoughDetails();
        getLastServiceValues();
        serviceDate.setText(getTodaysDate());
        UserDetails userDetails=new UserDetails();
        String inspection1= UserDetails.getInsid();
        Toast.makeText(ServiceEntryDetails.this,inspection1,Toast.LENGTH_LONG).show();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerCricketers2.setLayoutManager(layoutManager);
        cricketersList2 = (ArrayList<Cricketer>) getIntent().getExtras().getSerializable("list");
        recyclerCricketers2.setAdapter(new CricketerAdapter(cricketersList2));


        submitbtnservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(currentvisithourvalue.getText().toString()) && !TextUtils.isEmpty(currentvisitoilvalue.getText().toString())
                        &&!TextUtils.isEmpty(currentvisitgreasevalue.getText().toString())&&!TextUtils.isEmpty(specificComplaint.getText().toString())&&!TextUtils.isEmpty(currentpartsupplier.getText().toString())
                        &&!TextUtils.isEmpty(engineerobservation.getText().toString())&&!TextUtils.isEmpty(partsweared.getText().toString())&&!TextUtils.isEmpty(operatorname.getText().toString())
                        &&!TextUtils.isEmpty(operatorphonenumber.getText().toString())&&!TextUtils.isEmpty(afterservicereport.getText().toString())&&!TextUtils.isEmpty(serviceamountcollected.getText().toString())
                        &&!TextUtils.isEmpty(accompainedby.getText().toString())&&!TextUtils.isEmpty(customerfeedback.getText().toString()))
                {
                    dtbaseservice.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.exists()) {

                                int ids = (int) snapshot.getChildrenCount();
                                DateFormat df1=new SimpleDateFormat("yy");
                                String year=df1.format(Calendar.getInstance().getTime());
                                InspectionNO=year+"INS"+ids;
                                inspectionno.setText(InspectionNO);

                                String hourValue = currentvisithourvalue.getText().toString();
                                String underWarranty=underwarranty.getSelectedItem().toString();
                                String InStock=instock.getSelectedItem().toString();
                                String oilValue = currentvisitoilvalue.getText().toString();
                                String greaseValue=currentvisitgreasevalue.getText().toString();
                                String specific_Complaint=specificComplaint.getText().toString();
                                String currentParts_Supplier=currentpartsupplier.getText().toString();
                                String engineerObservation=engineerobservation.getText().toString();
                                String partsWeared=partsweared.getText().toString();
                                String operatorName=operatorname.getText().toString();
                                String operatorPhoneNumber=operatorphonenumber.getText().toString();
                                String afterServiceReport=afterservicereport.getText().toString();
                                String serviceAmountCollected=serviceamountcollected.getText().toString();
                                String AccopaniedBy=accompainedby.getText().toString();
                                String customerFeedback=customerfeedback.getText().toString();

                                CompanyID company_ID=new CompanyID();
                                Service_company_Id=company_ID.getCOMPanyID();

                                ServiceEntryGathering modellist=new ServiceEntryGathering(hourValue,oilValue,greaseValue,underWarranty,InStock,specific_Complaint,currentParts_Supplier,engineerObservation,getTodaysDate(),getTimewitham(),partsWeared,operatorName,operatorPhoneNumber,afterServiceReport,serviceAmountCollected,AccopaniedBy,customerFeedback,Service_OnCall,Service_company_Id,InspectionNO,cricketersList2);
                                dtbaseservice.child(InspectionNO).setValue(modellist);


                                LastVisitServiceValues lastVisitServiceValues=new LastVisitServiceValues(hourValue,oilValue,greaseValue,InspectionNO,Service_company_Id);
                                dtbaselastvisitvalue.child(InspectionNO).setValue(lastVisitServiceValues);


                                String service_id_ins=InspectionNO;

                                if(flagservice==0) {
                                    Query query = dtservicecompdetails.orderByChild("companyId").equalTo(Service_company_Id);

                                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                            if (snapshot1.exists()) {

                                                for (DataSnapshot ds : snapshot1.getChildren()) {
                                                    CompanyDetails models = new CompanyDetails(ds.child("companyId").getValue(String.class), ds.child("companyName").getValue(String.class), ds.child("companyPhoneNumber").getValue(String.class), ds.child("companyAddress").getValue(String.class), ds.child("companyEmail").getValue(String.class));


                                                    SearchServiceDetails searchServiceDetails = new SearchServiceDetails(models.getCompanyId(), service_id_ins, models.getCompanyName(), models.getCompanyPhoneNumber(), models.getCompanyEmail(), models.getCompanyAddress());
                                                    dtsearchservice.child(InspectionNO).setValue(searchServiceDetails);
                                                }

                                            } else {
                                                Toast.makeText(ServiceEntryDetails.this, "Please Register the Company First", Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    flagservice=1;
                                }

                                Toast.makeText(ServiceEntryDetails.this,"All details are Stored",Toast.LENGTH_LONG).show();

                                SendingMail();


                                Intent intent=new Intent(ServiceEntryDetails.this,UserLeveloneDashboard.class);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else{
                    Toast.makeText(ServiceEntryDetails.this,"Fill All Details!",Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private void getLastServiceValues() {

        CompanyID company_ID = new CompanyID();
        String Service_comp_Id = company_ID.getCOMPanyID();


        DatabaseReference dtlastvisit=FirebaseDatabase.getInstance().getReference("Service_LastVisit_Value");

        Query query = dtlastvisit.orderByChild("service_com_Id").equalTo(Service_comp_Id);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    for (DataSnapshot ds : snapshot.getChildren()) {
                        LastVisitServiceValues models = new LastVisitServiceValues(ds.child("hour_Value").getValue(String.class), ds.child("oil_Value").getValue(String.class), ds.child("grease_Value").getValue(String.class), ds.child("inspection_no").getValue(String.class), ds.child("service_com_Id").getValue(String.class));
                        String lastvalue_hour=models.getHour_Value();
                        String lastvalue_oil=models.getOil_Value();
                        String lastvalue_grease=models.getGrease_Value();

                        lastvisithourvalue.setText(lastvalue_hour);
                        lastvisitoilvalue.setText(lastvalue_oil);
                        lastvisitgreasevalue.setText(lastvalue_grease);
                    }

                }else{
                    Toast.makeText(ServiceEntryDetails.this,"No Last Values Found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getEnoughDetails() {

        dtgetservice.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    int id = (int) snapshot.getChildrenCount();
                    DateFormat df1=new SimpleDateFormat("yy");
                    String year=df1.format(Calendar.getInstance().getTime());
                     inspection=year+"INS"+id;
                    inspectionno.setText(inspection);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void SendingMail() {

        String sEmail, sPassword, rEmail,rCname;
        sEmail = "ssannthoshkumar@gmail.com";
        sPassword = "Santhosh*2";

        CompanyID companyID=new CompanyID();
        rEmail =CompanyID.getComPany_Email_id().trim();
        rCname=CompanyID.getComPany_Name();

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sEmail, sPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sEmail));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(rEmail.trim()));

            message.setSubject("Payment Details");

            message.setText("Payment Done for "+rCname+" company  The amount you paid is " + serviceamountcollected.getText().toString() + ". The Obserrvation done by our Engineer is "+engineerobservation.getText().toString());

            new SendMail().execute(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private class SendMail extends AsyncTask<Message, String, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ServiceEntryDetails.this
                    , "Please Wait", "Sending Mail...", true, false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();
            if (s.equals("Success")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ServiceEntryDetails.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
                builder.setMessage("Mail send successfully");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            } else {
                Toast.makeText(getApplicationContext()
                        , "Something went wrong ?", Toast.LENGTH_SHORT).show();
            }
        }
    }





    private String getTodaysDate(){
        return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    }
    private String getTimewitham(){
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }
}