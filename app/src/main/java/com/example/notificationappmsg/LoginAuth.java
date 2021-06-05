package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LoginAuth extends AppCompatActivity {

    EditText loginemail,loginpassword,loginname,loginuserlevel,loginphonenumber;
    Button btntologin;

    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_auth);

        loginemail=findViewById(R.id.etloginemail);
        loginpassword=findViewById(R.id.etloginpassword);
        btntologin=findViewById(R.id.btnlogin);
        loginname=findViewById(R.id.usernamelogin);
        loginuserlevel=findViewById(R.id.userlevelnumber);
        loginphonenumber=findViewById(R.id.userphonenumber);


        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();


        btntologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  if(!TextUtils.isEmpty(loginemail.getText().toString())&&!TextUtils.isEmpty(loginpassword.getText().toString())&&!TextUtils.isEmpty(loginname.getText().toString())&&!TextUtils.isEmpty(loginuserlevel.getText().toString())&&!TextUtils.isEmpty(loginphonenumber.getText().toString()))
                validate(loginemail.getText().toString(), loginpassword.getText().toString(),loginname.getText().toString(),loginuserlevel.getText().toString(),loginphonenumber.getText().toString());
            }
        });

    }

    private void validate(String userEmailName, String userPassword,String userNames,String UserLevelNumber,String UserPhoneNumber) {

        auth.createUserWithEmailAndPassword(userEmailName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser user=auth.getCurrentUser();
                    Toast.makeText(LoginAuth.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    DocumentReference df=db.collection("users").document(user.getUid());
                    Map<String, Object> userinfo = new HashMap<>();
                    userinfo.put("Name", userNames);
                    userinfo.put("Email", userEmailName);
                    userinfo.put("PhoneNumber", UserPhoneNumber);
                    userinfo.put("UserLevel", UserLevelNumber);
                    df.set(userinfo);
                    startActivity(new Intent(LoginAuth.this, IntroActivity.class));
                }else{
                    Toast.makeText(LoginAuth.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });}



}