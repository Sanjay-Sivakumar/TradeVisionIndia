package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class RegistrationPage extends AppCompatActivity {

    FirebaseAuth authReset = FirebaseAuth.getInstance();
    EditText resetemail;
    Button resetbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        resetemail = findViewById(R.id.etregisteremail);
        resetbtn = findViewById(R.id.btnregister);

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(resetemail.getText().toString())) {
                    ResetCall(resetemail.getText().toString());
                }else{
                    Toast.makeText(RegistrationPage.this,"Please fill the details",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void ResetCall(String resetEmail) {

        authReset.sendPasswordResetEmail(resetEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(RegistrationPage.this, "Reset Email is send", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationPage.this, IntroActivity.class));
                } else {
                    Toast.makeText(RegistrationPage.this, "Enter Correct Email", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

}