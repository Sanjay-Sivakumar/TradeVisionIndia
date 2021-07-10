package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class UserSearch extends AppCompatActivity {

    DatabaseReference userRef;
    private ListView listView;
    private AutoCompleteTextView txtsearch;
    String USERID;

    List<model> modelList;
    public int k=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        userRef=FirebaseDatabase.getInstance().getReference("UserData");
        listView= findViewById(R.id.usersearchlist);
        txtsearch= findViewById(R.id.usersearch);


        modelList = new ArrayList<>();

        InitalizeGetData();

        ValueEventListener event=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelList.clear();
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
            ArrayAdapter<String> Aadapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,names);
            txtsearch.setAdapter(Aadapter);
            txtsearch.setOnItemClickListener((parent, view, position, id) -> {
                String fname=txtsearch.getText().toString();
                SearchUser(fname);
            });

        }else
        {
            Toast.makeText(UserSearch.this,"No User Found",Toast.LENGTH_LONG).show();
        }
    }

    private void SearchUser(String fname) {

        Query query=userRef.orderByChild("namedb").equalTo(fname);

        k=0;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {

                    model1[] model1s= new model1[100];
                    ArrayList<String> listtuser=new ArrayList<>();
                    for(DataSnapshot ds:snapshot.getChildren())
                    {
                         model1s[k]= new model1(ds.child("namedb").getValue(String.class),ds.child("phnodb").getValue(String.class),ds.child("emaildb").getValue(String.class),ds.child("purldb").getValue(String.class));
                        listtuser.add(model1s[k].getNamedb()+"\n"+model1s[k].getPhnodb()+"\n"+model1s[k].getEmaildb());
                        k++;
                    }

                    ArrayAdapter<String> AAdapter=new ArrayAdapter<>(UserSearch.this, android.R.layout.simple_list_item_1,listtuser);
                    listView.setAdapter(AAdapter);
                    listView.setOnItemClickListener((parent, view, position, id) -> {

                        USERID = model1s[position].getPhnodb();
                        Intent intent = new Intent(UserSearch.this,UserprofilePage.class);
                        intent.putExtra("USERID", USERID);
                        startActivity(intent);
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

    private void InitalizeGetData()
    {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {

                modelList.clear();

                if(dataSnapshot.exists()) {


                    model[] models= new model[100];
                    ArrayList<String> listtuser=new ArrayList<>();
                    for(DataSnapshot ds:dataSnapshot.getChildren())
                    {
                        models[k]= new model(ds.child("namedb").getValue(String.class),ds.child("phnodb").getValue(String.class),ds.child("emaildb").getValue(String.class),ds.child("purldb").getValue(String.class));
                        listtuser.add(models[k].getNamedb()+"\n"+models[k].getPhnodb()+"\n"+models[k].getEmaildb());
                        k++;
                    }

                    ArrayAdapter<String> AAdapter=new ArrayAdapter<>(UserSearch.this, android.R.layout.simple_list_item_1,listtuser);
                    listView.setAdapter(AAdapter);
                    listView.setOnItemClickListener((parent, view, position, id) -> {

                        USERID = models[position].getPhnodb();
                        Intent intent = new Intent(UserSearch.this,UserprofilePage.class);
                        intent.putExtra("USERID", USERID);
                        startActivity(intent);
                    });
                }else{
                    Toast.makeText(UserSearch.this,"Snapshot Not found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError databaseError) {

            }
        });
    }

}
