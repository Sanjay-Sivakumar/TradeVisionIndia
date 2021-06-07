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

public class OrderLossViewing extends AppCompatActivity {

    ListView listViewOrderLossList;
    ArrayList<OrderLossDetails> orderLossDetails;
    DatabaseReference databaseorderLoss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_loss_viewing);

        databaseorderLoss = FirebaseDatabase.getInstance().getReference("Order_Loss_Details");
        listViewOrderLossList = (ListView) findViewById(R.id.listVieworderslossview);

        orderLossDetails = new ArrayList<>();


    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseorderLoss.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                orderLossDetails.clear();

                Toast.makeText(OrderLossViewing.this,"111",Toast.LENGTH_LONG).show();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    OrderLossDetails busno = postSnapshot.getValue(OrderLossDetails.class);
                    //adding artist to the list
                    orderLossDetails.add(busno);
                }

                //creating adapter
                OrderLossDetailsList BustimeAdapter = new OrderLossDetailsList(OrderLossViewing.this, orderLossDetails);
                //attaching adapter to the listview
                listViewOrderLossList.setAdapter(BustimeAdapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}