package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class ProfileEditingAdminPage extends AppCompatActivity {


    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference noteRef;


    Button btntoadminupdate;
    EditText adminstarrating,admincompanyvisit,adminusernamefb,adminuserphonenumberfb,adminuserworkpositionfb,adminuseraddressfb,adminuserworkzonefb,adminuseremailfb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editing_admin_page);

        btntoadminupdate=findViewById(R.id.adminuserprofileupdate);
        adminusernamefb=findViewById(R.id.usernameedit2fb);
        adminuserphonenumberfb=findViewById(R.id.userphonenumberedit2fb);
        adminuseraddressfb=findViewById(R.id.userpermanentaddress2fb);
        adminuseremailfb=findViewById(R.id.usercontactemail2fb);
        adminuserworkpositionfb=findViewById(R.id.userworkpositionfb);
        adminuserworkzonefb=findViewById(R.id.userworkzonefb);
        adminstarrating=findViewById(R.id.userstarratigfb);
        admincompanyvisit=findViewById(R.id.usercompanyvisitedfb);




        btntoadminupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(adminusernamefb.getText().toString())&&!TextUtils.isEmpty( adminuserphonenumberfb.getText().toString())&&!TextUtils.isEmpty( adminuseraddressfb.getText().toString())&&!TextUtils.isEmpty( adminuseremailfb.getText().toString())&&!TextUtils.isEmpty( adminuserworkzonefb.getText().toString())&&!TextUtils.isEmpty( adminuserworkpositionfb.getText().toString())&&!TextUtils.isEmpty( adminstarrating.getText().toString())&&!TextUtils.isEmpty( admincompanyvisit.getText().toString())){
                    UpdateUserProfile(adminusernamefb.getText().toString(), adminuserphonenumberfb.getText().toString(), adminuseraddressfb.getText().toString(), adminuseremailfb.getText().toString(), adminuserworkpositionfb.getText().toString(), adminuserworkzonefb.getText().toString(), adminstarrating.getText().toString(), admincompanyvisit.getText().toString());
                }else{
                    Toast.makeText(ProfileEditingAdminPage.this,"Please Fill All Details ",Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    public void UpdateUserProfile(String userName,String userPhoneNumber,String userEmailId,String userAddress,String userCurrentPostion,String userWorkingZone,String StarRating,String NoOfComapanyVisited)
    {
        noteRef = db.collection("UsersProfile").document(adminuserphonenumberfb.getText().toString());
        Map<String, Object> note = new HashMap<>();
        note.put("UserName", userName);
        note.put("UserPhoneNumber", userPhoneNumber);
        note.put("UserAddress", userAddress);
        note.put("UserEmailId", userEmailId);
        note.put("UserCurrentPosition", userCurrentPostion);
        note.put("UserWorkingZone", userWorkingZone);
        note.put("UserStarRating", StarRating);
        note.put("UserCompanyVisited", NoOfComapanyVisited);
        noteRef.set(note, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProfileEditingAdminPage.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(ProfileEditingAdminPage.this,ProfileImageUpload.class);
                        intent.putExtra("userName",userName);
                        intent.putExtra("UserPhone",userPhoneNumber);
                        intent.putExtra("userEmail",userEmailId);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileEditingAdminPage.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void onBackPressed() {
        askToClose();
    }

    private void askToClose (){
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileEditingAdminPage.this);
        builder.setMessage("Are you sure you want to quit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent intent=new Intent(ProfileEditingAdminPage.this,UserLeveloneDashboard.class);
                startActivity(intent);
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
