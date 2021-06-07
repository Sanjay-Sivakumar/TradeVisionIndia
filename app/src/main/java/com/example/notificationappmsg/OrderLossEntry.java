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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
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

public class OrderLossEntry extends AppCompatActivity {

    TextView orderlossDate,loss_oen_id;

    EditText competitorsBrand,competitorsPrice,lossPrice,ExisitingBrand,lossScope,otherReason;

    public String Order_Loss_oen,Order_Loss_CompanyName,Order_Loss_CompanyPhone,Order_Loss_CompanyEmail,Order_Loss_Address,Order_Loss_Comp_ID;

    DatabaseReference dtbaseorderLoss,dtlossdatesandtimes,dtpaymentloss;

    Button btnsubmitLoss;

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

                    SendingMail();

                    Toast.makeText(OrderLossEntry.this,"Your Order Has Been Closed",Toast.LENGTH_LONG);
                    Intent intent=new Intent(OrderLossEntry.this,UserLeveloneDashboard.class);
                    startActivity(intent);


                }
            }
        });



    }

    private void SetTheDetails(String oenNumbers, String dates, String times) {

        dtpaymentloss=FirebaseDatabase.getInstance().getReference("Payment_Details").child(dates+""+times);

        Toast.makeText(OrderLossEntry.this,dates+""+times,Toast.LENGTH_LONG).show();

        dtpaymentloss.child("netprice").setValue("-1");


    }

    private void SetAllTheValues() {
    }

    private void SendingMail() {

        String sEmail, sPassword, rEmail;
        sEmail = "ssannthoshkumar@gmail.com";
        sPassword = "Santhosh*2";

        rEmail = Order_Loss_CompanyEmail.trim();

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

            message.setSubject("Order Loss");

            message.setText("Your Order Number "+Order_Loss_oen+" Has been droped.");

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

            progressDialog = ProgressDialog.show(OrderLossEntry.this
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
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderLossEntry.this);
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