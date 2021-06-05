package com.example.notificationappmsg;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServiceDashboardPage extends AppCompatActivity {


    EditText etTo,etSubject,etMessage;
    Button btSend;
    String sEmail,sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_dashboard_page);


        etTo = findViewById(R.id.et_to);
        etSubject=findViewById(R.id.et_subject);
        etMessage=findViewById(R.id.et_message);
        btSend=findViewById(R.id.bt_send);

        sEmail="ssannthoshkumar@gmail.com";
        sPassword="Santhosh*2";

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Properties properties = new Properties();
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.port","587");

                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sEmail,sPassword);
                    }
                });

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(sEmail));

                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(etTo.getText().toString().trim()));

                    message.setSubject(etSubject.getText().toString().trim());

                    message.setText(etMessage.getText().toString().trim());

                    new SendMail().execute(message);

                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class SendMail extends AsyncTask<Message,String,String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ServiceDashboardPage.this
                    ,"Please Wait","Sending Mail...",true,false);
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
            if(s.equals("Success")){
                AlertDialog.Builder builder = new AlertDialog.Builder(ServiceDashboardPage.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
                builder.setMessage("Mail send successfully");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        etTo.setText("");
                        etSubject.setText("");
                        etMessage.setText("");
                    }
                });

                builder.show();
            }else {
                Toast.makeText(getApplicationContext()
                        ,"Something went wrong ?",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
