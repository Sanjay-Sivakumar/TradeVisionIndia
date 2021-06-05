package com.example.notificationappmsg;

import androidx.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class TakingOrder extends AppCompatActivity {

    EditText ordertph,orderstage,orderwashing,basicPrice,transportationPrice,tpcName,tpcAmount,advancePay,orderRemarks,chequeRTGSdetails;
    TextView orderDate,oenNumber,gstAmount,tcsAmount,netPriceOrder;

    Button btnsubmitOrder;
    Button imgcalculate;
    LinearLayout addequipments;
    Spinner brandOrder;
    public String comPanyID;

    DatabaseReference dtbaseorder,dtgetorder,dtpayment,dtbasedates,dtbasecompdetails,dtsearch;

    public String order_company_name,order_company_phone,order_company_email,order_company_address;

    RecyclerView recyclerCricketers1;
    ArrayList<Cricketer> cricketersList1 = new ArrayList<>();

    public String TCSAmount,GSTAmount,TOTAL_PRICE,NET_PRICE,Oen_Numbers,ChildCount;

    public double totalPrice;

    public int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taking_order);


        ordertph=findViewById(R.id.ettphorder);
        orderstage=findViewById(R.id.etstageorder);
        orderwashing=findViewById(R.id.etwashingorder);
        orderDate=findViewById(R.id.orderdate);
        oenNumber=findViewById(R.id.orderNumber);
        basicPrice=findViewById(R.id.basicprice);
        transportationPrice=findViewById(R.id.transportprice);
        tpcName=findViewById(R.id.ettpcname);
        tpcAmount=findViewById(R.id.ettpcamount);

        brandOrder=findViewById(R.id.brandspinnerorder);

        advancePay=findViewById(R.id.etadvanceorder);

        orderRemarks=findViewById(R.id.et_remarksorder);
        chequeRTGSdetails=findViewById(R.id.chequertgsdetails);
        gstAmount=findViewById(R.id.textViewgst);
        tcsAmount=findViewById(R.id.textViewtcsamount);
        netPriceOrder=findViewById(R.id.textViewnetprice);


        recyclerCricketers1=findViewById(R.id.recycler_cricketersorder);

        btnsubmitOrder=findViewById(R.id.submitorder);
        imgcalculate=findViewById(R.id.calculatenetamount);

        addequipments=findViewById(R.id.addequipment1);

        dtbaseorder= FirebaseDatabase.getInstance().getReference("Order_Details");
        dtgetorder = FirebaseDatabase.getInstance().getReference("Order_Details");
        dtpayment=FirebaseDatabase.getInstance().getReference("Payment_Details");
        dtbasedates=FirebaseDatabase.getInstance().getReference("Order_Dates");
        dtbasecompdetails=FirebaseDatabase.getInstance().getReference("CompanyDetails");
        dtsearch=FirebaseDatabase.getInstance().getReference("Search_Engine_Details");


        orderDate.setText(getTodaysDate());
        getEnoughDetails();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerCricketers1.setLayoutManager(layoutManager);
        cricketersList1 = (ArrayList<Cricketer>) getIntent().getExtras().getSerializable("list");
        recyclerCricketers1.setAdapter(new CricketerAdapter(cricketersList1));

        imgcalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(transportationPrice.getText().toString())&&!TextUtils.isEmpty(basicPrice.getText().toString())&&!TextUtils.isEmpty(advancePay.getText().toString()))
                {
                    CalculateNetAmount(transportationPrice.getText().toString(),basicPrice.getText().toString(),advancePay.getText().toString());

                }else{
                    Toast.makeText(TakingOrder.this,"Fill enough details!",Toast.LENGTH_LONG).show();
                }
            }
        });




        btnsubmitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(ordertph.getText().toString()) && !TextUtils.isEmpty(orderstage.getText().toString())
                &&!TextUtils.isEmpty(orderwashing.getText().toString())&&!TextUtils.isEmpty(basicPrice.getText().toString())&&!TextUtils.isEmpty(transportationPrice.getText().toString())
                &&!TextUtils.isEmpty(tpcName.getText().toString())&&!TextUtils.isEmpty(tpcAmount.getText().toString())&&!TextUtils.isEmpty(advancePay.getText().toString())
                &&!TextUtils.isEmpty(chequeRTGSdetails.getText().toString())&&!TextUtils.isEmpty(orderRemarks.getText().toString()))
                {
                    dtbaseorder.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.exists()) {

                                int ids = (int) snapshot.getChildrenCount();
                                DateFormat df1=new SimpleDateFormat("yy");
                                String year=df1.format(Calendar.getInstance().getTime());
                                Oen_Numbers=year+"OEN"+ids;
                                oenNumber.setText(Oen_Numbers);

                                String orderTPH = ordertph.getText().toString();
                                String OrderBrand=brandOrder.getSelectedItem().toString();
                                String orderStage = orderstage.getText().toString();
                                String orderWashing=orderwashing.getText().toString();
                                String BasicPrice=basicPrice.getText().toString();
                                String TransportationPrice=transportationPrice.getText().toString();
                                String AdvancePAY=advancePay.getText().toString();
                                String TCPName=tpcName.getText().toString();
                                String TCPAmount=tpcAmount.getText().toString();
                                String Cheque_RTGS_deatis=chequeRTGSdetails.getText().toString();
                                String Order_Remarks=orderRemarks.getText().toString();

                                CompanyID company_ID=new CompanyID();
                                comPanyID=company_ID.getCOMPanyID();

                                OrderEntryDetails modellist=new OrderEntryDetails(orderTPH,OrderBrand,orderStage,orderWashing,TCPName,TCPAmount,Cheque_RTGS_deatis,Order_Remarks,getTodaysDate(),getTimewitham(),BasicPrice,GSTAmount,TransportationPrice,AdvancePAY,TOTAL_PRICE,NET_PRICE,TCSAmount,comPanyID,Oen_Numbers,cricketersList1);
                                dtbaseorder.child(Oen_Numbers).setValue(modellist);

                                paymentmodel1 paymentmodel1=new paymentmodel1(comPanyID,Oen_Numbers,NET_PRICE,AdvancePAY,Cheque_RTGS_deatis,getTodaysDate(),getTimewitham());

                                paymentmodel1.setCompany_ID(comPanyID);

                                paymentmodel1.setOEN_ID(Oen_Numbers);
                                paymentmodel1.setNETPRICE(NET_PRICE);

                                paymentmodel1.setAmountPaid(AdvancePAY);
                                paymentmodel1.setChequeDetails(Cheque_RTGS_deatis);

                                paymentmodel1.setORderDate(getTodaysDate());
                                paymentmodel1.setORderTime(getTimewitham());

                                dtpayment.child(getTodaysDate()+""+getTimewitham()).setValue(paymentmodel1);

                                OrderDatesAndTime orderDatesAndTime=new OrderDatesAndTime(getTodaysDate(),getTimewitham(),Oen_Numbers,comPanyID);
                                orderDatesAndTime.setOrderDate(getTodaysDate());
                                orderDatesAndTime.setOrderTime(getTimewitham());
                                orderDatesAndTime.setOEN_NUMBER_ID(Oen_Numbers);
                                orderDatesAndTime.setCompanyId(comPanyID);
                                dtbasedates.child(Oen_Numbers).setValue(orderDatesAndTime);

                              //  GetEnoughDetailsOfCompany(comPanyID);

                                String Order_id_oen=Oen_Numbers;

                                if(flag==0) {
                                    Query query = dtbasecompdetails.orderByChild("companyId").equalTo(comPanyID);

                                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                            if (snapshot1.exists()) {

                                                for (DataSnapshot ds : snapshot1.getChildren()) {
                                                    CompanyDetails models = new CompanyDetails(ds.child("companyId").getValue(String.class), ds.child("companyName").getValue(String.class), ds.child("companyPhoneNumber").getValue(String.class), ds.child("companyAddress").getValue(String.class), ds.child("companyEmail").getValue(String.class));
                                                    order_company_name = models.getCompanyName();
                                                    order_company_phone = models.getCompanyPhoneNumber();
                                                    order_company_email = models.getCompanyEmail();
                                                    order_company_address = models.getCompanyAddress();


                                                    SearchResultDetails searchResultDetails = new SearchResultDetails(models.getCompanyId(), Order_id_oen, models.getCompanyName(), models.getCompanyPhoneNumber(), models.getCompanyEmail(), models.getCompanyAddress());
                                                    Toast.makeText(TakingOrder.this,comPanyID,Toast.LENGTH_LONG).show();
                                                    searchResultDetails.setCompanyId(comPanyID);
                                                    searchResultDetails.setCompanyName(order_company_name);
                                                    searchResultDetails.setOenId(Order_id_oen);
                                                    searchResultDetails.setCompanyPhone(order_company_phone);
                                                    searchResultDetails.setCompanyEmail(order_company_email);
                                                    searchResultDetails.setCompayAddress(order_company_address);

                                                    dtsearch.child(Order_id_oen).setValue(searchResultDetails);
                                                }

                                            } else {
                                                Toast.makeText(TakingOrder.this, "Please Register the Company First", Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    flag=1;
                                }


                                Toast.makeText(TakingOrder.this,"All details are Stored",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(TakingOrder.this,OrderPhotoUpload.class);
                                intent.putExtra("OEN_ID",Oen_Numbers);
                                startActivity(intent);


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else{
                    Toast.makeText(TakingOrder.this,"Fill All Details!",Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private void GetEnoughDetailsOfCompany(String comPanyID) {

    }

    private void getEnoughDetails() {

        dtgetorder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    int id = (int) snapshot.getChildrenCount();
                    DateFormat df1=new SimpleDateFormat("yy");
                    String year=df1.format(Calendar.getInstance().getTime());
                    Oen_Numbers=year+"OEN"+id;
                    oenNumber.setText(Oen_Numbers);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void CalculateNetAmount(String transportationPrice, String basicPrice,String advancepay) {

        try {
            int basicprice= Integer.parseInt(basicPrice);
            int transportPrice=Integer.parseInt(transportationPrice);
            double tcsamount= basicprice*.01;
            double gstamount=basicprice*0.18;
            DecimalFormat df=new DecimalFormat();
            df.setMaximumFractionDigits(2);
            TCSAmount=df.format(tcsamount);
            GSTAmount=df.format(gstamount);
            totalPrice=gstamount+transportPrice+basicprice+tcsamount;
            int advancePAY= Integer.parseInt(advancepay);
            double NetPrice = totalPrice-advancePAY;
            NET_PRICE = df.format(NetPrice);
            TOTAL_PRICE=df.format(totalPrice);
            tcsAmount.setText(TCSAmount);
            gstAmount.setText(GSTAmount);
            netPriceOrder.setText(TOTAL_PRICE);

        }catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private String getTodaysDate(){
        return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    }
    private String getTimewitham(){
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }

}