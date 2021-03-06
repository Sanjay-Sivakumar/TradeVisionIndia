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
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class VisitOfferImageUpload extends AppCompatActivity {


    String Offer_Copy;

    StorageReference orderStorageReference3;
    FirebaseFirestore orderFirebaseFirestore3;
    DatabaseReference databaseorderdata;
    Uri imageUri;
    ImageView copyofoffer;

    String santab =new String("1");
    String santab1 =new String("2");
    String santab2 =new String("3");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_offer_image_upload);


        copyofoffer=findViewById(R.id.copyofpo);

        Offer_Copy= getIntent().getStringExtra("OFFERPIC");

        orderStorageReference3= FirebaseStorage.getInstance().getReference("OFFER_PIC");
        orderFirebaseFirestore3=FirebaseFirestore.getInstance();


        copyofoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });



    }

    private void choosePicture() {
        Intent intent =new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try{
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null) {
                imageUri = data.getData();

                Bitmap objectBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                copyofoffer.setImageBitmap(objectBitmap);
                uploadpicture(Offer_Copy,imageUri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void uploadpicture(String id, Uri imagelink) {

        String linktoupload=imagelink.toString();

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("uploading image.......");
        pd.show();

        ModelOrder modellist = new ModelOrder(id,linktoupload);
        databaseorderdata.child(id).setValue(modellist);

        try {
            if (imageUri != null) {
                String nameoftheimage = id+getExtension(imageUri);
                StorageReference imgRef = orderStorageReference3.child(nameoftheimage);

                UploadTask objectUploadTask = imgRef.putFile(imageUri);
                objectUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return imgRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Map<String, String> objectmap = new HashMap<>();
                            objectmap.put("url", task.getResult().toString());
                            orderFirebaseFirestore3.collection("OfferPhoto").document(id)
                                    .set(objectmap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            pd.dismiss();
                                            Toast.makeText(VisitOfferImageUpload.this, "Image is uploaded", Toast.LENGTH_LONG).show();
                                            FirebaseAuth authUser=FirebaseAuth.getInstance();
                                            FirebaseUser UserAuth=authUser.getCurrentUser();
                                            CheckUserAccessLevel6(UserAuth.getUid());
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    pd.dismiss();
                                    Toast.makeText(VisitOfferImageUpload.this, "Image is not uploaded", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                });
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private  String getExtension(Uri uri) {
        try {
            ContentResolver objectContentResolver = getContentResolver();
            MimeTypeMap objectMimeTypeMap = MimeTypeMap.getSingleton();

            return objectMimeTypeMap.getExtensionFromMimeType(objectContentResolver.getType(uri));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        askToClose();
    }

    private void askToClose (){
        AlertDialog.Builder builder = new AlertDialog.Builder(VisitOfferImageUpload.this);
        builder.setMessage("Are you sure you want to quit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                FirebaseAuth authUser=FirebaseAuth.getInstance();
                FirebaseUser UserAuth=authUser.getCurrentUser();
                CheckUserAccessLevel6(UserAuth.getUid());
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

    private void CheckUserAccessLevel6(String uid) {
        DocumentReference df=FirebaseFirestore.getInstance().collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String levelCHecker=documentSnapshot.getString("UserLevel");

                if(Objects.equals(levelCHecker, santab))
                {
                    Intent intent = new Intent(VisitOfferImageUpload.this,ServiceEngineerDashboard.class);
                    startActivity(intent);
                }
                else if(Objects.equals(levelCHecker, santab1)){
                    Intent intent = new Intent(VisitOfferImageUpload.this, BusinessExecutiveDashboard.class);
                    startActivity(intent);
                }else
                {
                    Intent intent = new Intent(VisitOfferImageUpload.this, TeritoryManagerDashboard.class);
                    startActivity(intent);
                }

            }
        });
    }
}