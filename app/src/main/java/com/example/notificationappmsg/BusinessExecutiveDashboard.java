package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class BusinessExecutiveDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout2;
    NavigationView navigationView2;
    Toolbar toolbar2;
    ImageView image0,image4,image5,image6,image7,image8,image9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_executive_dashboard);

        drawerLayout2 = findViewById(R.id.drawer_layout2);
        navigationView2 = findViewById(R.id.nav_view2);
        toolbar2 = findViewById(R.id.toolbar2);
        image5 = findViewById(R.id.imageView5);
        image6 = findViewById(R.id.imageView6);
        image7 = findViewById(R.id.imageView7);
        image8 = findViewById(R.id.imageView8);
        image9 = findViewById(R.id.imageView9);
        image0 = findViewById(R.id.imageView0);
        image4 = findViewById(R.id.imageView4);



        image0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(BusinessExecutiveDashboard.this, CompanyDetailsEntering.class);
                BusinessExecutiveDashboard.this.startActivity(myIntent);
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(BusinessExecutiveDashboard.this, CompanySearch.class);
                myIntent.putExtra("RequestCode", "1");
                BusinessExecutiveDashboard.this.startActivity(myIntent);
            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(BusinessExecutiveDashboard.this, CompanySearch.class);
                myIntent.putExtra("RequestCode", "2");
                BusinessExecutiveDashboard.this.startActivity(myIntent);
            }
        });
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(BusinessExecutiveDashboard.this, SearchEngine.class);
                myIntent.putExtra("RequestCode", "1");
                BusinessExecutiveDashboard.this.startActivity(myIntent);
            }
        });
        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(BusinessExecutiveDashboard.this, SearchEngine.class);
                myIntent.putExtra("RequestCode", "2");
                BusinessExecutiveDashboard.this.startActivity(myIntent);
            }
        });
        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(BusinessExecutiveDashboard.this, SearchEngine.class);
                myIntent.putExtra("RequestCode", "3");
                BusinessExecutiveDashboard.this.startActivity(myIntent);
            }
        });
        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(BusinessExecutiveDashboard.this, SearchEngine.class);
                myIntent.putExtra("RequestCode", "4");
                BusinessExecutiveDashboard.this.startActivity(myIntent);
            }
        });

        setSupportActionBar(toolbar2);

        //Menu menu = navigationView2.getMenu();
        //menu.findItem(R.id.nav_logout).setVisible(false);
        //menu.findItem(R.id.nav_profile).setVisible(false);

        navigationView2.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout2,toolbar2,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout2.addDrawerListener(toggle);
        toggle.syncState();

        navigationView2.setNavigationItemSelectedListener(this);

        navigationView2.setCheckedItem(R.id.nav_home);

    }


    @Override
    public void onBackPressed() {
        if(drawerLayout2.isDrawerOpen(GravityCompat.START)){
            drawerLayout2.closeDrawer(GravityCompat.START);
        }
        else {

            askToClose();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_search:
                Intent intent3 = new Intent(BusinessExecutiveDashboard.this,UserSearch.class);
                startActivity(intent3);
                break;
            case R.id.nav_profile:
                Intent intent4 = new Intent(BusinessExecutiveDashboard.this,UserprofilePage.class);
                startActivity(intent4);
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent5 = new Intent(BusinessExecutiveDashboard.this,LoginPage.class);
                startActivity(intent5);
                break;
        }
        drawerLayout2.closeDrawer(GravityCompat.START);
        return true;
    }
    private void askToClose (){
        AlertDialog.Builder builder = new AlertDialog.Builder(BusinessExecutiveDashboard.this);
        builder.setMessage("Are you sure you want to quit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finishAffinity();
                System.exit(0);
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