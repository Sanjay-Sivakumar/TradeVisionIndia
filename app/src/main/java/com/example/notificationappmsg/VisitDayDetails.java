package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class VisitDayDetails extends AppCompatActivity {


    EditText visitmeetingperson,visittph,visitstage,valueofoffer,visitremarks,accompainedperson;
    TextView visitdate,visittime,lastvisitdetails,vistinextdate;

    Button btnsubmitvisit;
    LinearLayout addequipments;
    Spinner visitdetails,purposeofvisit;
    public String CompanyVisitID;

    private static final String TAG = "Next Visit";

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public String date1;

    public int flagvisit=0;



    DatabaseReference dtbasevisit,dtbaselastdeatils,dtbaselast,dtlastdates,dtlastdates1;

    RecyclerView recyclerCricketers2;
    ArrayList<Cricketer> cricketersList2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_day_details);

        CompanyID companyID=new CompanyID();
        CompanyVisitID=companyID.getCOMPanyID();


        dtlastdates=FirebaseDatabase.getInstance().getReference("Last_Visit_Dates");
        dtlastdates1=FirebaseDatabase.getInstance().getReference("Last_Visit_Dates");
        dtbasevisit= FirebaseDatabase.getInstance().getReference("Visit_Details");
        dtbaselastdeatils = FirebaseDatabase.getInstance().getReference("Last_Vist_Details");


        visitdate=findViewById(R.id.visitdate);
        visittime=findViewById(R.id.visittime);
        lastvisitdetails=findViewById(R.id.lastvisitdetails);
        vistinextdate=findViewById(R.id.visitnextdate);

        visitmeetingperson=findViewById(R.id.meetingpersonvist);
        visittph=findViewById(R.id.ettphvisit);
        visitstage=findViewById(R.id.etstagevisit);
        valueofoffer=findViewById(R.id.valueofoffer);
        visitremarks=findViewById(R.id.et_remarksvisit);
        accompainedperson=findViewById(R.id.accompainedbypersonvisit);
        recyclerCricketers2=findViewById(R.id.recycler_cricketersvisit);

        btnsubmitvisit=findViewById(R.id.submitvisitinput);

        visitdetails=findViewById(R.id.visitinputdetails);
        purposeofvisit=findViewById(R.id.visitdetailspurpose);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerCricketers2.setLayoutManager(layoutManager);
        cricketersList2 = (ArrayList<Cricketer>) getIntent().getExtras().getSerializable("list");
        recyclerCricketers2.setAdapter(new CricketerAdapter(cricketersList2));

        visitdate.setText(getTodaysDate());
        visittime.setText(getTimewitham());

        try {
            Query query = dtlastdates.orderByChild("dates_Company_ID").equalTo(CompanyVisitID);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            LastVisitDates models = new LastVisitDates(ds.child("dates_Company_ID").getValue(String.class), ds.child("dateLast").getValue(String.class), ds.child("timeLast").getValue(String.class));
                            String CID = models.getDates_Company_ID();
                            String Vdates = models.getDateLast();
                            String Vtimes = models.getTimeLast();
                            setDetails(CID, Vdates, Vtimes);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
       }catch (Exception e) {
            Toast.makeText(VisitDayDetails.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


        btnsubmitvisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(visitmeetingperson.getText().toString())&&!TextUtils.isEmpty(visittph.getText().toString())&&!TextUtils.isEmpty(visitstage.getText().toString())
                &&!TextUtils.isEmpty(valueofoffer.getText().toString())&&!TextUtils.isEmpty(visitremarks.getText().toString())&&!TextUtils.isEmpty(accompainedperson.getText().toString())
                &&flagvisit==1)
                {
                    String meetingPerson=visitmeetingperson.getText().toString();
                    String tph=visittph.getText().toString();
                    String stage=visitstage.getText().toString();
                    String valueOfOffer=valueofoffer.getText().toString();
                    String visitRemarks=visitremarks.getText().toString();
                    String accompainedPerson=accompainedperson.getText().toString();
                    String visitDetails=visitdetails.getSelectedItem().toString();
                    String PurposeOfVisit=purposeofvisit.getSelectedItem().toString();

                    VisitDetails VD=new VisitDetails(getTodaysDate(),getTimewitham(),meetingPerson,tph,stage,valueOfOffer,visitRemarks,accompainedPerson,visitDetails,PurposeOfVisit,date1,CompanyVisitID,cricketersList2);
                    dtbasevisit.child(CompanyVisitID+""+getTodaysDate()+""+getTimewitham()).setValue(VD);

                    LastVisitDetails lastVisitDetails=new LastVisitDetails(CompanyVisitID,getTodaysDate(),getTimewitham(),visitRemarks);
                    dtbaselastdeatils.child(CompanyVisitID+""+getTodaysDate()+""+getTimewitham()).setValue(lastVisitDetails);

                    LastVisitDates lastVisitDates=new LastVisitDates(CompanyVisitID,getTodaysDate(),getTimewitham());
                    dtlastdates1.child(CompanyVisitID).setValue(lastVisitDates);


                    Toast.makeText(VisitDayDetails.this,"Details are Uploaded Successfully",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(VisitDayDetails.this,"Fill The Enough Details And Do The Enough Task To Complete This Action",Toast.LENGTH_LONG).show();
                }

            }
        });


        vistinextdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagvisit=1;
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        VisitDayDetails.this,
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

                date1 = month + "-" + day + "-" + year;
                vistinextdate.setText(date1);
            }
        };



    }


    private String getTodaysDate(){
        return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    }
    private String getTimewitham(){
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }


    private void setDetails(String cid,String dates,String times) {


        dtbaselast=FirebaseDatabase.getInstance().getReference("Last_Vist_Details").child(cid+""+dates+""+times);



        dtbaselast.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {

                if (ds.exists()) {

                    LastVisitDetails busno = ds.getValue(LastVisitDetails.class);
                    String lastvisitdetail=busno.getVisitRemarks();
                    String lastvisitdate=busno.getDateVisited();
                    String seter="Last visited date was "+lastvisitdate+"\n The Remark was "+lastvisitdetail;
                    lastvisitdetails.setText(seter);

                }
                else{
                    Toast.makeText(VisitDayDetails.this,"Snapshot Not found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}