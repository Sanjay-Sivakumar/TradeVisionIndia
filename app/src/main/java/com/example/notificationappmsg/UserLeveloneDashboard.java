package com.example.notificationappmsg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class UserLeveloneDashboard extends AppCompatActivity {

    Button btn1,btn2,btn3;
    FirebaseFirestore fsstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_levelone_dashboard);

        btn1=findViewById(R.id.Userprofile);
        btn2=findViewById(R.id.button2);
        btn3=findViewById(R.id.button3);
        fsstore=FirebaseFirestore.getInstance();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserLeveloneDashboard.this,CompanyDetailsEntering.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth authUser=FirebaseAuth.getInstance();
                FirebaseUser UserAuth=authUser.getCurrentUser();
                Getphnumber(UserAuth.getUid());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserLeveloneDashboard.this,CompanySearch.class);
                startActivity(intent);
            }
        });

    }

    private void Getphnumber(String uid) {
        DocumentReference df=fsstore.collection("users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String levelCHecker=documentSnapshot.getString("PhoneNumber");
                Toast.makeText(UserLeveloneDashboard.this,levelCHecker,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UserLeveloneDashboard.this, UserprofilePage.class);
                    intent.putExtra("USERID",levelCHecker);
                    startActivity(intent);

            }
        });
    }
}