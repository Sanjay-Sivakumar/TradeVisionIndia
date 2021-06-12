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

public class Visit extends AppCompatActivity {

    ImageView offerphoto;
    FirebaseFirestore reteriveimageFirestore2;
    DocumentReference objectDocumentReference2;
    public String OFFER_PHOTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);


        offerphoto=findViewById(R.id.offeromageReterive);
        OFFER_PHOTO=getIntent().getStringExtra("OFFERPHOTO");



        reteriveimageFirestore2=FirebaseFirestore.getInstance();
        downloadingimage(OFFER_PHOTO);

    }

    public void downloadingimage(String listr)
    {


        try {



            objectDocumentReference2=reteriveimageFirestore2.collection("OfferPhoto").document(listr);

            objectDocumentReference2.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    String linkofimage=documentSnapshot.getString("url");
                    Glide.with(Visit.this)
                            .load(linkofimage)
                            .into(offerphoto);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Visit.this,"Fails to get image",Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}