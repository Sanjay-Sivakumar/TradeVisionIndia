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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PaymentDetailsViewing extends AppCompatActivity {

    ListView listViewPaymentList;
    ArrayList<paymentmodel1> paymentmodel1s;
    DatabaseReference databasePaymentList;


    public String oenPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details_viewing);

        databasePaymentList = FirebaseDatabase.getInstance().getReference("Payment_Details");
        listViewPaymentList = (ListView) findViewById(R.id.listViewpaymentview);


        paymentmodel1s = new ArrayList<>();

        oenPayment=getIntent().getStringExtra("RequestOen");

        Toast.makeText(PaymentDetailsViewing.this,oenPayment,Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        Query query=databasePaymentList.orderByChild("oen_ID").equalTo(oenPayment);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {

                paymentmodel1s.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    paymentmodel1 busno = postSnapshot.getValue(paymentmodel1.class);

                    paymentmodel1s.add(busno);
                }

                //creating adapter
                PaymentDetailsList BustimeAdapter = new PaymentDetailsList(PaymentDetailsViewing.this, paymentmodel1s);

                listViewPaymentList.setAdapter(BustimeAdapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}