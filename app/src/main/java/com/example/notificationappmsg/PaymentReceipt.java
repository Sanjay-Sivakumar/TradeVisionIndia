package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

import java.text.DecimalFormat;
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

public class PaymentReceipt extends AppCompatActivity {

    TextView prdate,proen,cnmaepr,netpricepr,remainingpr;
    EditText amountbytoday,chequedetails;

    Button btn1,btn2;

    public String Company_Name_payment,Order_Net_Price,oen_number_id,cOMPany_Id,AmountPaid,Lastnetamount,Company_Email_Payment;
    public String Oen_Payment;

    public DatabaseReference dforderpayment,dfcompanypayment,dtorderdates,dttesting,dtpayment1,dtorderdates1;

    public int flagPayment=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_receipt);

        CompanyID companyID=new CompanyID();
        cOMPany_Id=companyID.getCOMPanyID();
        Company_Name_payment=CompanyID.getComPany_Name();
        Company_Email_Payment=CompanyID.getComPany_Email_id();
        Oen_Payment=CompanyID.getCOMPany_OEN();


        prdate=findViewById(R.id.tv1);
        proen=findViewById(R.id.tv2);
        cnmaepr=findViewById(R.id.tv3);
        netpricepr=findViewById(R.id.tv4);
        amountbytoday=findViewById(R.id.et1);
        chequedetails=findViewById(R.id.et2);
        remainingpr=findViewById(R.id.tv5);
        btn1=findViewById(R.id.buttonpr);
        btn2=findViewById(R.id.buttonpr1);

        prdate.setText(getTodaysDate());
        cnmaepr.setText(Company_Name_payment);
        proen.setText(Oen_Payment);


        dforderpayment=FirebaseDatabase.getInstance().getReference("Payment_Details");
        dtorderdates=FirebaseDatabase.getInstance().getReference("Order_Dates");
        dtpayment1=FirebaseDatabase.getInstance().getReference("Payment_Details");
        dtorderdates1=FirebaseDatabase.getInstance().getReference("Order_Dates");


        Query query=dtorderdates.orderByChild("oen_NUMBER_ID").equalTo(Oen_Payment);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        OrderDatesAndTime models = new OrderDatesAndTime(ds.child("orderDate").getValue(String.class), ds.child("orderTime").getValue(String.class), ds.child("oen_NUMBER_ID").getValue(String.class), ds.child("companyId").getValue(String.class));
                        String oenNumbers = models.getOEN_NUMBER_ID();
                        String dates = models.getOrderDate();
                        String times = models.getOrderTime();
                        setDetails(oenNumbers,dates,times);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagPayment=1;
                if(!TextUtils.isEmpty(amountbytoday.getText().toString()))
                {
                    String amtpay=amountbytoday.getText().toString();
                    try {
                        int amtPay= Integer.parseInt(amtpay);
                        Lastnetamount=Lastnetamount.replaceAll(",","");
                        double neTPrice=Double.parseDouble(Lastnetamount);
                        if(neTPrice==-1)
                        {
                            Toast.makeText(PaymentReceipt.this,"The Order has been closed",Toast.LENGTH_LONG).show();
                        }
                        else if(neTPrice==0)
                        {

                            Toast.makeText(PaymentReceipt.this,"The Payment had completed for this order",Toast.LENGTH_LONG).show();
                        }
                        else {
                            if(neTPrice>=amtPay)
                            {
                                Double Remaining = neTPrice - amtPay;
                                DecimalFormat df = new DecimalFormat();
                                df.setMaximumFractionDigits(2);
                                Order_Net_Price = df.format(Remaining);
                                remainingpr.setText(Order_Net_Price);
                            }
                            else{
                                Toast.makeText(PaymentReceipt.this,"Please enter the correct Amount",Toast.LENGTH_LONG).show();
                            }


                        }

                    }catch (Exception e) {
                        Toast.makeText(PaymentReceipt.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(PaymentReceipt.this,"Please fill Enough details",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(amountbytoday.getText().toString())&&!TextUtils.isEmpty(chequedetails.getText().toString())&& flagPayment==1)
                {
                    paymentmodel1 paymentmodel1=new paymentmodel1(cOMPany_Id,oen_number_id,Order_Net_Price,amountbytoday.getText().toString(),chequedetails.getText().toString(),getTodaysDate(),getTimewitham());

                    paymentmodel1.setCompany_ID(cOMPany_Id);

                    paymentmodel1.setOEN_ID(oen_number_id);
                    paymentmodel1.setNETPRICE(Order_Net_Price);

                    paymentmodel1.setAmountPaid(amountbytoday.getText().toString());
                    paymentmodel1.setChequeDetails(chequedetails.getText().toString());

                    paymentmodel1.setORderDate(getTodaysDate());
                    paymentmodel1.setORderTime(getTimewitham());

                    dforderpayment.child(getTodaysDate()+""+getTimewitham()+""+oen_number_id).setValue(paymentmodel1);

                    OrderDatesAndTime orderDatesAndTime=new OrderDatesAndTime(getTodaysDate(),getTimewitham(),oen_number_id,cOMPany_Id);
                    orderDatesAndTime.setOrderDate(getTodaysDate());
                    orderDatesAndTime.setOrderTime(getTimewitham());
                    orderDatesAndTime.setOEN_NUMBER_ID(oen_number_id);
                    orderDatesAndTime.setCompanyId(cOMPany_Id);
                    dtorderdates1.child(oen_number_id).setValue(orderDatesAndTime);

                    Toast.makeText(PaymentReceipt.this,"All details are updated",Toast.LENGTH_LONG).show();
                    SendingMail();

                }else{
                    Toast.makeText(PaymentReceipt.this,"Please Do Enough task to complete this action",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void SendingMail() {

        String sEmail, sPassword, rEmail;
        sEmail = "ssannthoshkumar@gmail.com";
        sPassword = "Santhosh*2";

        rEmail =Company_Email_Payment.trim();

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

            message.setText("Payment Done for "+Company_Name_payment+"company \nThe Order Entry number is " + oen_number_id + "\nThe amount you paid is " + amountbytoday.getText().toString() + "\nThe Remaining amount is " + Order_Net_Price+"\n");

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

                progressDialog = ProgressDialog.show(PaymentReceipt.this
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(PaymentReceipt.this);
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

    private void setDetails(String oenNumber,String dates,String times) {


        dtpayment1=FirebaseDatabase.getInstance().getReference("Payment_Details").child(dates+""+times+""+oenNumber);



        dtpayment1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {

                if (ds.exists()) {

                    paymentmodel1 models = new paymentmodel1(ds.child("company_ID").getValue(String.class), ds.child("oen_ID").getValue(String.class), ds.child("netprice").getValue(String.class), ds.child("amountPaid").getValue(String.class),ds.child("chequeDetails").getValue(String.class),ds.child("orderDate").getValue(String.class),ds.child("orderTime").getValue(String.class));
                    oen_number_id = models.getOEN_ID();
                    Lastnetamount = models.getNETPRICE();
                    String nETPrice=models.getNETPRICE();
                    netpricepr.setText(nETPrice);

                }
                else{
                    Toast.makeText(PaymentReceipt.this,"Snapshot Not found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private String getTodaysDate(){
        return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    }
    private String getTimewitham(){
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }
}