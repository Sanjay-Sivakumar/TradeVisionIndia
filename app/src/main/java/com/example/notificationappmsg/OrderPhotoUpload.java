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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OrderPhotoUpload extends AppCompatActivity {

    String order_OEN_ID;

    StorageReference orderStorageReference;
    FirebaseFirestore orderFirebaseFirestore;
    DatabaseReference databaseorderdata;
    Uri imageUri;
    ImageView copyofpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_photo_upload);


        copyofpo=findViewById(R.id.copyofpo);

        order_OEN_ID= getIntent().getStringExtra("OEN_ID");

        orderStorageReference= FirebaseStorage.getInstance().getReference("Order_PIC");
        orderFirebaseFirestore=FirebaseFirestore.getInstance();
        databaseorderdata = FirebaseDatabase.getInstance().getReference("Order_PIC");

        copyofpo.setOnClickListener(new View.OnClickListener() {
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
                copyofpo.setImageBitmap(objectBitmap);
                uploadpicture(order_OEN_ID,imageUri);
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
                StorageReference imgRef = orderStorageReference.child(nameoftheimage);

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
                            orderFirebaseFirestore.collection("OrderPhoto").document(id)
                                    .set(objectmap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            pd.dismiss();
                                            Toast.makeText(OrderPhotoUpload.this, "Image is uploaded", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(OrderPhotoUpload.this,UserLeveloneDashboard.class));
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    pd.dismiss();
                                    Toast.makeText(OrderPhotoUpload.this, "Image is not uploaded", Toast.LENGTH_LONG).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderPhotoUpload.this);
        builder.setMessage("Are you sure you want to quit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent intent=new Intent(OrderPhotoUpload.this,UserLeveloneDashboard.class);
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