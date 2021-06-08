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

import java.util.HashMap;
import java.util.Map;

public class LoginAuth extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;

    EditText loginemail,loginpassword,loginphonenumber,loginname,loginuserlevel,loginworkzone,loginworkposition,loginuseraddress,loginuserid;
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
        loginworkposition=findViewById(R.id.createuserworkposition);
        loginworkzone=findViewById(R.id.createuserworkzone);
        loginuseraddress=findViewById(R.id.createuseraddress);
        loginuserid=findViewById(R.id.createuserid);

        createuser=findViewById(R.id.btncreateuser);


        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();




        createuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(loginemail.getText().toString())&&!TextUtils.isEmpty(loginpassword.getText().toString())&&!TextUtils.isEmpty(loginname.getText().toString())
                        &&!TextUtils.isEmpty(loginuserlevel.getText().toString())&&!TextUtils.isEmpty(loginphonenumber.getText().toString())
                        &&!TextUtils.isEmpty(loginuseraddress.getText().toString())&&!TextUtils.isEmpty(loginuserid.getText().toString())
                &&!TextUtils.isEmpty(loginworkposition.getText().toString())&&!TextUtils.isEmpty(loginworkzone.getText().toString()))
                validate(loginemail.getText().toString(), loginpassword.getText().toString(),loginname.getText().toString(),loginuserlevel.getText().toString(),loginphonenumber.getText().toString(),loginuseraddress.getText().toString(),loginworkzone.getText().toString(),loginworkposition.getText().toString(),loginuserid.getText().toString());
            }
        });

    }

    private void validate(String userEmailName, String userPassword,String userNames,String UserLevelNumber,String UserPhoneNumber,String UserAddress,String UserWorkPosition,String UserWorkZone,String UserId) {

        auth.createUserWithEmailAndPassword(userEmailName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser user=auth.getCurrentUser();
                    DocumentReference df=db.collection("UsersProfile").document(user.getUid());
                    Toast.makeText(LoginAuth.this,user.getUid(),Toast.LENGTH_LONG).show();
                    Map<String, Object> note = new HashMap<>();
                    note.put("UserName", userNames);
                    note.put("UserPhoneNumber", UserPhoneNumber);
                    note.put("UserAddress", UserAddress);
                    note.put("UserEmailId", userEmailName);
                    note.put("UserCurrentPosition", UserWorkPosition);
                    note.put("UserWorkingZone", UserWorkZone);
                    note.put("UserLevel",UserLevelNumber );
                    note.put("UserId", UserId);
                    df.set(note, SetOptions.merge())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(LoginAuth.this, "Profile Created", Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(LoginAuth.this,ProfileImageUpload.class);
                            intent.putExtra("userName",userNames);
                            intent.putExtra("UserPhone",UserPhoneNumber);
                            intent.putExtra("userEmail",userEmailName);
                            startActivity(intent);
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LoginAuth.this, "Error!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }else{
                    Toast.makeText(LoginAuth.this, "Creation of User Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });}



}