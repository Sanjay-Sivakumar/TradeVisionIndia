 package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginAuth extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;

    EditText loginemail,loginpassword,loginphonenumber,loginname,loginuserlevel;
    Spinner loginworkzone,loginworkunderteritory;
    EditText loginuseraddress,loginuserid,loginworkposition;;
    Button createuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_auth);

        loginemail=findViewById(R.id.createuseremail);
        loginpassword=findViewById(R.id.createuserpassword);
        loginname=findViewById(R.id.createusername);
        loginuserlevel=findViewById(R.id.createuserlevel);
        loginphonenumber=findViewById(R.id.createuserphonenumber);
        loginworkposition=findViewById(R.id.createuserworkpositon);
        loginworkzone=findViewById(R.id.createuserworkzone);
        loginuseraddress=findViewById(R.id.createuseraddress);
        loginuserid=findViewById(R.id.createuserid);

        createuser=findViewById(R.id.btncreateuser);
        loginworkunderteritory=findViewById(R.id.createuserunderteritory);


        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        List<String> zoneList = new ArrayList<>();
        List<String> teritoryList = new ArrayList<>();


        zoneList.add("Erode");
        zoneList.add("Kancheepuram");
        zoneList.add("Chennai");

        teritoryList.add("T1");
        teritoryList.add("T2");
        teritoryList.add("T3");



        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,zoneList);
        loginworkzone.setAdapter(arrayAdapter1);

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,teritoryList);
        loginworkunderteritory.setAdapter(arrayAdapter2);


        createuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(loginemail.getText().toString())&&!TextUtils.isEmpty(loginpassword.getText().toString())&&!TextUtils.isEmpty(loginname.getText().toString())
                        &&!TextUtils.isEmpty(loginuserlevel.getText().toString())&&!TextUtils.isEmpty(loginphonenumber.getText().toString())
                        &&!TextUtils.isEmpty(loginuseraddress.getText().toString())&&!TextUtils.isEmpty(loginuserid.getText().toString())
                &&!TextUtils.isEmpty(loginworkposition.getText().toString())&&!TextUtils.isEmpty(loginworkzone.getSelectedItem().toString())&&!TextUtils.isEmpty(loginworkunderteritory.getSelectedItem().toString()))
                ShowPopUpConfirmation(loginemail.getText().toString(), loginpassword.getText().toString(),loginname.getText().toString(),loginuserlevel.getText().toString(),loginphonenumber.getText().toString(),loginuseraddress.getText().toString(),loginworkposition.getText().toString(),loginworkzone.getSelectedItem().toString(),loginuserid.getText().toString(),loginworkunderteritory.getSelectedItem().toString());

            }
        });

    }
    private void ShowPopUpConfirmation(String userEmailName, String userPassword,String userNames,String UserLevelNumber,String UserPhoneNumber,String UserAddress,String UserWorkPosition,String UserWorkZone,String UserId,String UserUnderTeritory)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginAuth.this);
        builder.setMessage("Please Confirm That You Entered Details Is Correct");
        builder.setCancelable(true);
        builder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}