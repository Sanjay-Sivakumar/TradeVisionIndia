package com.example.notificationappmsg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminDashborad extends AppCompatActivity {

    Button btnlog,btnadduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashborad);

        btnlog=findViewById(R.id.btnadd);
        btnadduser=findViewById(R.id.btnlog);

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminDashborad.this,LoginAuth.class);
                startActivity(intent);
            }
        });

        btnadduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminDashborad.this,LoginPage.class);
                startActivity(intent);
            }
        });

    }

}