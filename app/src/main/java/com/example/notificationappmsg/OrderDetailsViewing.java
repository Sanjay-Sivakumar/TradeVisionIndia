package com.example.notificationappmsg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OrderDetailsViewing extends AppCompatActivity {



    ListView listViewOrderList;
    ListView listViewOrdermodel;
    ArrayList<OrderEntryDetails> orderEntryDetailList;
    DatabaseReference databaseOrderList;
    ArrayList<String> cricketers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_viewing);

        databaseOrderList = FirebaseDatabase.getInstance().getReference("Order_Details");
        listViewOrderList = (ListView) findViewById(R.id.listViewordersview);
        listViewOrdermodel=findViewById(R.id.listVieworderlistview);

        orderEntryDetailList = new ArrayList<>();
        cricketers=new ArrayList<String>();



    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseOrderList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                orderEntryDetailList.clear();
                cricketers.clear();
                ArrayAdapter adapter=new ArrayAdapter<String>(OrderDetailsViewing.this,R.layout.row_cricketer,cricketers);
                listViewOrdermodel.setAdapter(adapter);


                Toast.makeText(OrderDetailsViewing.this,"111",Toast.LENGTH_LONG).show();
                //iterating through all the nodes
               for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                   //getting artist
                   OrderEntryDetails busno = postSnapshot.getValue(OrderEntryDetails.class);
                   //adding artist to the list
                   orderEntryDetailList.add(busno);
                   String oen=busno.getOEN_ID();

                   Toast.makeText(OrderDetailsViewing.this,String.valueOf(dataSnapshot.child(oen).child("listEqiupments").getChildrenCount()),Toast.LENGTH_LONG).show();


                   for (DataSnapshot ds : dataSnapshot.child(oen).child("listEqiupments").getChildren()) {

                       if (ds.exists()) {
                           Cricketer crket = ds.getValue(Cricketer.class);

                           String modelname=crket.getModelName();
                           String modelnumber=crket.getModelNumber();
                           cricketers.add(modelname);
                           cricketers.add(modelnumber);

                       } else {
                           Toast.makeText(OrderDetailsViewing.this, "Snapshot not found 2", Toast.LENGTH_LONG).show();
                       }

                   }
                   adapter.notifyDataSetChanged();
               }

                //creating adapter
                OrderEntryDetailsList BustimeAdapter = new OrderEntryDetailsList(OrderDetailsViewing.this, orderEntryDetailList);
                //attaching adapter to the listview
                listViewOrderList.setAdapter(BustimeAdapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}