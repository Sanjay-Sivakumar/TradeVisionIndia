package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class UserprofilePage extends AppCompatActivity {

    private TextView fullName,phoneNumber,EmailId,Adress,workPosition,WorkZone,starRate,CompanyVisit;
    private FirebaseFirestore profileFirestore;
    private FirebaseFirestore reteriveimageFirestore;
    private DocumentReference objectDocumentReference;
    private DocumentReference profileDocumentReference;
    private ImageView imagedownloaded;
    

    FirebaseFirestore fstoredit;
    private LinearLayout editprofilechecker;


    String sanedit =new String("3");
    public String USER_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile_page);


         USER_ID=getIntent().getStringExtra("USERID");
         

        fullName=findViewById(R.id.fullnameview);
        phoneNumber=findViewById(R.id.phonenumberview);
        EmailId=findViewById(R.id.emailidview);
        Adress=findViewById(R.id.addressview);
        workPosition=findViewById(R.id.workingpositionview);
        WorkZone=findViewById(R.id.workingzoneview);
        starRate=findViewById(R.id.starratingview);
        CompanyVisit=findViewById(R.id.companyvisitedview);

        editprofilechecker=findViewById(R.id.editprofilell);

        imagedownloaded = findViewById(R.id.profileimageuserview);
        reteriveimageFirestore=FirebaseFirestore.getInstance();
        profileFirestore=FirebaseFirestore.getInstance();
        fstoredit=FirebaseFirestore.getInstance();

        try {


            profileDocumentReference = profileFirestore.collection("UsersProfile").document(USER_ID);

            profileDocumentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    String name = documentSnapshot.getString("UserName");
                    String phone = documentSnapshot.getString("UserPhoneNumber");
                    String addr = documentSnapshot.getString("UserAddress");
                    String email = documentSnapshot.getString("UserEmailId");
                    String Position = documentSnapshot.getString("UserCurrentPosition");
                    String Workingzone = documentSnapshot.getString("UserWorkingZone");
                    String StarRating = documentSnapshot.getString("UserStarRating");
                    String CompanyVisited = documentSnapshot.getString("UserCompanyVisited");


                    fullName.setText(name);
                    phoneNumber.setText(phone);
                    EmailId.setText(email);
                    Adress.setText(addr);
                    workPosition.setText(Position);
                    WorkZone.setText(Workingzone);
                    starRate.setText(StarRating);
                    CompanyVisit.setText(CompanyVisited);

                    downloadingimage(USER_ID);


                }
            }).addOnFailureListener(e -> Toast.makeText(UserprofilePage.this, "Fails to get details", Toast.LENGTH_LONG).show());
        }catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        editprofilechecker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth authUser=FirebaseAuth.getInstance();
                FirebaseUser UserAuth=authUser.getCurrentUser();
                CheckUserAccessLevelEditing(UserAuth.getUid());

            }
        });

    }

    public void downloadingimage(String listr)
    {
        try {

            objectDocumentReference=reteriveimageFirestore.collection("profileLinks").document(listr);

            objectDocumentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    String linkofimage=documentSnapshot.getString("url");
                    Glide.with(UserprofilePage.this)
                            .load(linkofimage)
                            .into(imagedownloaded);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UserprofilePage.this,"Fails to get image",Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void CheckUserAccessLevelEditing(String uid) {
        DocumentReference df=fstoredit.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String levelCHecker=documentSnapshot.getString("UserLevel");


                if(Objects.equals(levelCHecker, sanedit))
                {
                    Intent intent = new Intent(UserprofilePage.this, ProfileEditingAdminPage.class);
                    startActivity(intent);
                }else
                {
                    Intent intent = new Intent(UserprofilePage.this, ProfileViewingPage.class);
                    startActivity(intent);
                }

            }
        });
    }
}