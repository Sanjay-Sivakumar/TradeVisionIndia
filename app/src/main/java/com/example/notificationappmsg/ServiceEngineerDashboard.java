package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ServiceEngineerDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout1;
    NavigationView navigationView1;
    Toolbar toolbar1;
    ImageView image1,image2,image3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_engineer_dashboard);

        drawerLayout1 = findViewById(R.id.drawer_layout1);
        navigationView1 = findViewById(R.id.nav_view1);
        toolbar1 = findViewById(R.id.toolbar1);
        image1 = findViewById(R.id.imageView1);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView3);


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ServiceEngineerDashboard.this, SecondActivity.class);
                ServiceEngineerDashboard.this.startActivity(myIntent);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ServiceEngineerDashboard.this, SecondActivity.class);
                ServiceEngineerDashboard.this.startActivity(myIntent);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ServiceEngineerDashboard.this, SecondActivity.class);
                ServiceEngineerDashboard.this.startActivity(myIntent);
            }
        });

        setSupportActionBar(toolbar1);

        //Menu menu = navigationView1.getMenu();
        //menu.findItem(R.id.nav_logout).setVisible(false);
        //menu.findItem(R.id.nav_profile).setVisible(false);

        navigationView1.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout1,toolbar1,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout1.addDrawerListener(toggle);
        toggle.syncState();

        navigationView1.setNavigationItemSelectedListener(this);

        navigationView1.setCheckedItem(R.id.nav_home);

    }


    @Override
    public void onBackPressed() {
        if(drawerLayout1.isDrawerOpen(GravityCompat.START)){
            drawerLayout1.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_search:
                Intent intent = new Intent(ServiceEngineerDashboard.this,SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                Intent intent1 = new Intent(ServiceEngineerDashboard.this,SecondActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_logout:
                Intent intent2 = new Intent(ServiceEngineerDashboard.this,SecondActivity.class);
                startActivity(intent2);
                break;
        }
        drawerLayout1.closeDrawer(GravityCompat.START);
        return true;
    }
}