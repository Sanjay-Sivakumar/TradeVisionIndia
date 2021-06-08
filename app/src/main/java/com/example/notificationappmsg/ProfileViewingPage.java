package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProfileViewingPage extends AppCompatActivity {


    private FirebaseFirestore db1 = FirebaseFirestore.getInstance();
    private DocumentReference noteRef1 ;

    Button btntouserupdate;
    EditText usernamefb,userphonenumberfb,useraddressfb,useremailfb;

    String santab =new String("1");
    String santab1 =new String("2");
    String santab2 =new String("3");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_viewing_page);

        btntouserupdate=findViewById(R.id.btntoupdateuser);
        usernamefb=findViewById(R.id.usernameedit1fb);
        userphonenumberfb=findViewById(R.id.userphonenumberedit1fb);
        useraddressfb=findViewById(R.id.userpermanentaddressfb);
        useremailfb=findViewById(R.id.usercontactemailfb);


        btntouserupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(usernamefb.getText().toString())&&!TextUtils.isEmpty(userphonenumberfb.getText().toString())&&!TextUtils.isEmpty(useremailfb.getText().toString())&&!TextUtils.isEmpty(useraddressfb.getText().toString())){
                    UserUpadateProfile(usernamefb.getText().toString(),userphonenumberfb.getText().toString(),useremailfb.getText().toString(),useraddressfb.getText().toString());
                }

            }
        });

    }
    private void UserUpadateProfile(String userName, String userPhoneNumber, String userAddress, String userEmailId)
    {

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        noteRef1 = db1.collection("UsersProfile").document(user.getUid());

        Map<String, Object> note = new HashMap<>();
        note.put("UserName", userName);
        note.put("UserPhoneNumber", userPhoneNumber);
        note.put("UserAddress", userAddress);
        note.put("UserEmailId", userEmailId);
        noteRef1.set(note, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProfileViewingPage.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(ProfileViewingPage.this,ProfileImageUpload.class);
                        intent.putExtra("userName",userName);
                        intent.putExtra("UserPhone",userPhoneNumber);
                        intent.putExtra("userEmail",userEmailId);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileViewingPage.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        askToClose();
    }

    private void askToClose (){
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileViewingPage.this);
        builder.setMessage("Are you sure you want to quit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                FirebaseAuth authUser=FirebaseAuth.getInstance();
                FirebaseUser UserAuth=authUser.getCurrentUser();
                CheckUserAccessLevel2(UserAuth.getUid());
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

    private void CheckUserAccessLevel2(String uid) {
        DocumentReference df=FirebaseFirestore.getInstance().collection("UsersProfile").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String levelCHecker=documentSnapshot.getString("UserLevel");

                if(Objects.equals(levelCHecker, santab))
                {
                    Intent intent = new Intent(ProfileViewingPage.this,ServiceEngineerDashboard.class);
                    startActivity(intent);
                }
                else if(Objects.equals(levelCHecker, santab1)){
                    Intent intent = new Intent(ProfileViewingPage.this, BusinessExecutiveDashboard.class);
                    startActivity(intent);
                }else
                {
                    Intent intent = new Intent(ProfileViewingPage.this, TeritoryManagerDashboard.class);
                    startActivity(intent);
                }

            }
        });
    }

}
