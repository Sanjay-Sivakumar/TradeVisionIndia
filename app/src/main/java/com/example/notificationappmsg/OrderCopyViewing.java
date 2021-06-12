package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class OrderCopyViewing extends AppCompatActivity {

    ImageView orderphoto;
    FirebaseFirestore reteriveimageFirestore1;
    DocumentReference objectDocumentReference1;
    public String ORDER_PHOTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_copy_viewing);

        orderphoto=findViewById(R.id.imageViewordercopy);
        ORDER_PHOTO=getIntent().getStringExtra("ORDERPHOTO");



        reteriveimageFirestore1=FirebaseFirestore.getInstance();
        downloadingimage(ORDER_PHOTO);

    }

    public void downloadingimage(String listr)
    {


        try {



            objectDocumentReference1=reteriveimageFirestore1.collection("OrderPhoto").document(listr);

            objectDocumentReference1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    String linkofimage=documentSnapshot.getString("url");
                    Glide.with(OrderCopyViewing.this)
                            .load(linkofimage)
                            .into(orderphoto);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(OrderCopyViewing.this,"Fails to get image",Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

}