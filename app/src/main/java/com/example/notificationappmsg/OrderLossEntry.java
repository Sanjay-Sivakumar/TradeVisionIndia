package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OrderLossEntry extends AppCompatActivity {

    TextView orderlossDate,loss_oen_id;

    EditText competitorsBrand,competitorsPrice,lossPrice,ExisitingBrand,lossScope,otherReason;

    public String Order_Loss_oen,Order_Loss_CompanyName,Order_Loss_CompanyPhone,Order_Loss_CompanyEmail,Order_Loss_Address,Order_Loss_Comp_ID;

    DatabaseReference dtbaseorderLoss,dtlossdatesandtimes,dtpaymentloss;

    Button btnsubmitLoss;

    String santab =new String("1");
    String santab1 =new String("2");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_loss_entry);

        orderlossDate=findViewById(R.id.orderlossdate);
        loss_oen_id=findViewById(R.id.orderlossNumber);
        btnsubmitLoss=findViewById(R.id.submitorderloss);
        competitorsBrand=findViewById(R.id.competitorsbrand);
        competitorsPrice=findViewById(R.id.competitorPrice);
        lossPrice=findViewById(R.id.textViewpricevalue);
        ExisitingBrand=findViewById(R.id.textViewexisitingbrand);
        lossScope=findViewById(R.id.textViewscope);
        otherReason=findViewById(R.id.otherReasonorderloss);

        CompanyID companyID=new CompanyID();
        Order_Loss_Comp_ID=companyID.getCOMPanyID();
        Order_Loss_oen= CompanyID.getCOMPany_OEN();
        Order_Loss_CompanyName=CompanyID.getComPany_Name();
        Toast.makeText(OrderLossEntry.this,Order_Loss_CompanyName,Toast.LENGTH_LONG).show();
        Order_Loss_CompanyPhone=CompanyID.getComPany_phone_number();
        Order_Loss_Address=CompanyID.getComPany_Address();
        Order_Loss_CompanyEmail=CompanyID.getComPany_Email_id();

        orderlossDate.setText(getTodaysDate());
        loss_oen_id.setText(Order_Loss_oen);

        dtbaseorderLoss= FirebaseDatabase.getInstance().getReference("Order_Loss_Details");
        dtlossdatesandtimes=FirebaseDatabase.getInstance().getReference("Order_Dates");

        Query query=dtlossdatesandtimes.orderByChild("oen_NUMBER_ID").equalTo(Order_Loss_oen);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        OrderDatesAndTime models = new OrderDatesAndTime(ds.child("orderDate").getValue(String.class), ds.child("orderTime").getValue(String.class), ds.child("oen_NUMBER_ID").getValue(String.class), ds.child("companyId").getValue(String.class));
                        String oenNumbers = models.getOEN_NUMBER_ID();
                        String dates = models.getOrderDate();
                        String times = models.getOrderTime();
                        SetTheDetails(oenNumbers,dates,times);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnsubmitLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(competitorsBrand.getText().toString())&&!TextUtils.isEmpty(competitorsPrice.getText().toString())
                &&!TextUtils.isEmpty(lossPrice.getText().toString())&&!TextUtils.isEmpty(ExisitingBrand.getText().toString())
                &&!TextUtils.isEmpty(lossScope.getText().toString())&&!TextUtils.isEmpty(otherReason.getText().toString()))
                {
                    String CompetitorsBrand=competitorsBrand.getText().toString();
                    String CompetitiorsPrice=competitorsPrice.getText().toString();
                    String LossScope=lossScope.getText().toString();
                    String Exisiting_Brand=ExisitingBrand.getText().toString();
                    String LossPrice=lossPrice.getText().toString();
                    String OtherReason=otherReason.getText().toString();

                    OrderLossDetails orderLossDetails=new OrderLossDetails(Order_Loss_Comp_ID,Order_Loss_oen,Order_Loss_CompanyName,Order_Loss_CompanyPhone,Order_Loss_CompanyEmail,Order_Loss_Address,CompetitorsBrand,CompetitiorsPrice,LossPrice,LossScope,Exisiting_Brand,OtherReason,getTodaysDate(),getTimewitham());
                    dtbaseorderLoss.child(Order_Loss_oen).setValue(orderLossDetails);
                    FirebaseAuth authUser=FirebaseAuth.getInstance();
                    FirebaseUser UserAuth=authUser.getCurrentUser();
                    CheckUserAccessLevel5(UserAuth.getUid());
                }
            }
        });



    }

    private void SetTheDetails(String oenNumbers, String dates, String times) {

        dtpaymentloss=FirebaseDatabase.getInstance().getReference("Payment_Details").child(dates+""+times+""+oenNumbers);

        dtpaymentloss.child("netprice").setValue("-1");


    }


    private String getTodaysDate(){
        return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    }
    private String getTimewitham(){
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }

    private void CheckUserAccessLevel5(String uid) {
        DocumentReference df= FirebaseFirestore.getInstance().collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String levelCHecker=documentSnapshot.getString("UserLevel");

                if(Objects.equals(levelCHecker, santab))
                {
                    Intent intent = new Intent(OrderLossEntry.this,ServiceEngineerDashboard.class);
                    startActivity(intent);
                }
                else if(Objects.equals(levelCHecker, santab1)){
                    Intent intent = new Intent(OrderLossEntry.this, BusinessExecutiveDashboard.class);
                    startActivity(intent);
                }else
                {
                    Intent intent = new Intent(OrderLossEntry.this, TeritoryManagerDashboard.class);
                    startActivity(intent);
                }

            }
        });
    }
}