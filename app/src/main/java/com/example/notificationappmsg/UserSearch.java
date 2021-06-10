package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class UserSearch extends AppCompatActivity {

    DatabaseReference userRef;
    private ListView listView;
    private AutoCompleteTextView txtsearch;
    String USERID;

    List<model> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        userRef=FirebaseDatabase.getInstance().getReference("UserData");
        listView=(ListView)findViewById(R.id.usersearchlist);
        txtsearch=(AutoCompleteTextView)findViewById(R.id.usersearch);


        modelList = new ArrayList<>();

        ValueEventListener event=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                populateSearch(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(UserSearch.this,"No User Found",Toast.LENGTH_LONG).show();
            }
        };
        userRef.addListenerForSingleValueEvent(event);

    }

    private void populateSearch(DataSnapshot snapshot) {
        ArrayList<String> names=new ArrayList<>();
        if(snapshot.exists())
        {
            for(DataSnapshot ds:snapshot.getChildren())
            {
                String Fname=ds.child("namedb").getValue(String.class);
                names.add(Fname);
            }
            ArrayAdapter Aadapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,names);
            txtsearch.setAdapter(Aadapter);
            txtsearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String fname=txtsearch.getText().toString();
                    SearchUser(fname);
                }
            });

        }else
        {
            Toast.makeText(UserSearch.this,"No User Found",Toast.LENGTH_LONG).show();
        }
    }

    private void SearchUser(String fname) {

        Query query=userRef.orderByChild("namedb").equalTo(fname);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    ArrayList<String> listtuser=new ArrayList<>();
                    for(DataSnapshot ds:snapshot.getChildren())
                    {
                        model models= new model(ds.child("namedb").getValue(String.class),ds.child("phnodb").getValue(String.class),ds.child("emaildb").getValue(String.class),ds.child("purldb").getValue(String.class));
                        listtuser.add(models.getNamedb()+"\n"+models.getPhnodb()+"\n"+models.getEmaildb());
                        USERID=models.getPhnodb();
                    }

                    ArrayAdapter AAdapter=new ArrayAdapter(UserSearch.this, android.R.layout.simple_list_item_1,listtuser);
                    listView.setAdapter(AAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent intent = new Intent(UserSearch.this,UserprofilePage.class);
                            intent.putExtra("USERID", USERID);
                            startActivity(intent);
                        }
                    });


                }else{
                    Toast.makeText(UserSearch.this,"No User Found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
