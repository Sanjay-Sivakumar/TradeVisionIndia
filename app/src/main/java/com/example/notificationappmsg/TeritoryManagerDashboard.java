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

public class TeritoryManagerDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

     DrawerLayout drawerLayout3;
     NavigationView navigationView3;
     Toolbar toolbar3;
     ImageView image10,image11,image12,image13,image14,image15,image16,image17,image18,image19;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teritory_manager_dashboard);

        drawerLayout3 = findViewById(R.id.drawer_layout3);
        navigationView3 = findViewById(R.id.nav_view3);
        toolbar3 = findViewById(R.id.toolbar3);
        image14 = findViewById(R.id.imageView14);
        image15 = findViewById(R.id.imageView15);
        image16 = findViewById(R.id.imageView16);
        image17 = findViewById(R.id.imageView17);
        image18 = findViewById(R.id.imageView18);
        image10 = findViewById(R.id.imageView10);
        image11= findViewById(R.id.imageView11);
        image12 = findViewById(R.id.imageView12);
        image13 = findViewById(R.id.imageView13);
        image19 = findViewById(R.id.imageView19);



        image13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TeritoryManagerDashboard.this, CompanyDetailsEntering.class);
                TeritoryManagerDashboard.this.startActivity(myIntent);
            }
        });
        image14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TeritoryManagerDashboard.this, CompanySearch.class);
                myIntent.putExtra("RequestCode", "2");
                TeritoryManagerDashboard.this.startActivity(myIntent);
            }
        });
        image15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TeritoryManagerDashboard.this, SearchEngine.class);
                myIntent.putExtra("RequestCode", "1");
                TeritoryManagerDashboard.this.startActivity(myIntent);
            }
        });
        image16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TeritoryManagerDashboard.this, SearchEngine.class);
                myIntent.putExtra("RequestCode", "2");
                TeritoryManagerDashboard.this.startActivity(myIntent);
            }
        });
        image17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TeritoryManagerDashboard.this, SearchEngine.class);
                myIntent.putExtra("RequestCode", "3");
                TeritoryManagerDashboard.this.startActivity(myIntent);
            }
        });
        image18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TeritoryManagerDashboard.this, SearchEngine.class);
                myIntent.putExtra("RequestCode", "4");
                TeritoryManagerDashboard.this.startActivity(myIntent);
            }
        });
        image10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TeritoryManagerDashboard.this, CompanySearch.class);
                myIntent.putExtra("RequestCode", "3");
                TeritoryManagerDashboard.this.startActivity(myIntent);
            }
        });
        image11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TeritoryManagerDashboard.this, ServiceSearchEngine.class);
                myIntent.putExtra("RequestCode", "1");
                TeritoryManagerDashboard.this.startActivity(myIntent);
            }
        });
        image12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TeritoryManagerDashboard.this, CompanySearch.class);
                myIntent.putExtra("RequestCode", "1");
                TeritoryManagerDashboard.this.startActivity(myIntent);
            }
        });

        image19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TeritoryManagerDashboard.this, LoginAuth.class);
                TeritoryManagerDashboard.this.startActivity(myIntent);
            }
        });

        setSupportActionBar(toolbar3);

        //Menu menu = navigationView3.getMenu();
        //menu.findItem(R.id.nav_logout).setVisible(false);
        //menu.findItem(R.id.nav_profile).setVisible(false);

        navigationView3.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout3,toolbar3,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout3.addDrawerListener(toggle);
        toggle.syncState();

        navigationView3.setNavigationItemSelectedListener(this);

        navigationView3.setCheckedItem(R.id.nav_home);

    }


    @Override
    public void onBackPressed() {
        if(drawerLayout3.isDrawerOpen(GravityCompat.START)){
            drawerLayout3.closeDrawer(GravityCompat.START);
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
                Intent intent6 = new Intent(TeritoryManagerDashboard.this,UserSearch.class);
                startActivity(intent6);
                break;
            case R.id.nav_profile:
                Intent intent7 = new Intent(TeritoryManagerDashboard.this,UserprofilePage.class);
                startActivity(intent7);
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent8 = new Intent(TeritoryManagerDashboard.this,LoginPage.class);
                startActivity(intent8);
                break;
        }
        drawerLayout3.closeDrawer(GravityCompat.START);
        return true;
    }



    private void askToClose (){
        AlertDialog.Builder builder = new AlertDialog.Builder(TeritoryManagerDashboard.this);
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