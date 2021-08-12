 package com.example.notificationappmsg;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


import static com.google.firebase.messaging.Constants.TAG;

 public class LoginAuth extends AppCompatActivity {


    EditText createUserName,createUserPhoneNumber,createUserEmailId,createUserPassword,createUserConfirmPassword;
    EditText createUserQualification,createUserAddress;
    Button createUserBtn;
    Spinner createUserGender,createUserWorkZone,createUserWorkPosition,createUserWorkUnderTeritory;

    TextView createUserDOb;
    TextView createUserDoJ;
    public String dateofBirth,dateofJoining;
    private DatePickerDialog.OnDateSetListener mDateSetListenerDOB,mDateSetListenerDOJ;
    public int flagDOB=1,flagDOJ=1;

    private FirebaseAuth creationAuth;
    private final FirebaseFirestore creationFS=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_auth);

        createUserDOb=findViewById(R.id.createuserdoB);
        createUserName=findViewById(R.id.createusername);
        createUserEmailId=findViewById(R.id.createuseremail);
        createUserPhoneNumber=findViewById(R.id.createuserphonenumber);
        createUserPassword=findViewById(R.id.createuserpassword);
        createUserConfirmPassword=findViewById(R.id.createuserconfirmpassword);
        createUserQualification=findViewById(R.id.createuserqualification);
        createUserAddress=findViewById(R.id.createuseraddress);
        createUserBtn=findViewById(R.id.btncreateuser);
        createUserGender=findViewById(R.id.createusergender);
        createUserWorkZone=findViewById(R.id.createuserworkzone);
        createUserWorkPosition=findViewById(R.id.createuserworkpositon);
        createUserWorkUnderTeritory=findViewById(R.id.createuserunderteritory);
        createUserDoJ=findViewById(R.id.createuserdoj);
        creationAuth=FirebaseAuth.getInstance();

        createUserDOb.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    LoginAuth.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListenerDOB,
                    year,month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });

        mDateSetListenerDOB = (datePicker, year, month, day) -> {
            month = month + 1;
            Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "-" + day + "-" + year);

            dateofBirth = month + "-" + day + "-" + year;
            createUserDOb.setText(dateofBirth);
            flagDOB=1;
        };

        createUserDoJ.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    LoginAuth.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListenerDOJ,
                    year,month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });

        mDateSetListenerDOJ = (datePicker, year, month, day) -> {
            month = month + 1;
            Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "-" + day + "-" + year);

            dateofJoining = month + "-" + day + "-" + year;
            createUserDoJ.setText(dateofJoining);
            flagDOJ=1;
        };



        createUserBtn.setOnClickListener(view -> {


            if(!TextUtils.isEmpty(createUserConfirmPassword.getText().toString())&&
                    !TextUtils.isEmpty(createUserPassword.getText().toString())&&
                    (createUserPassword.getText().toString().equals(createUserConfirmPassword.getText().toString())))
            {
                if(!TextUtils.isEmpty(createUserName.getText().toString()) &&
                        !TextUtils.isEmpty(createUserPhoneNumber.getText().toString()) &&
                        !TextUtils.isEmpty(createUserEmailId.getText().toString()) &&
                        !TextUtils.isEmpty(createUserQualification.getText().toString()) &&
                        !TextUtils.isEmpty(createUserAddress.getText().toString()) && flagDOB==1 && flagDOJ==1&&
                        createUserGender.getSelectedItemId()!=0 && createUserWorkPosition.getSelectedItemId()!=0
                        && createUserWorkUnderTeritory.getSelectedItemId()!=0 &&
                        createUserWorkZone.getSelectedItemId()!=0)
                {
                    if(Patterns.EMAIL_ADDRESS.matcher(createUserEmailId.getText().toString()).matches())
                    {
                        ConfirmToSubmit();

                    }else{
                        Toast.makeText(LoginAuth.this,"Please Enter Correct EmailId",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(LoginAuth.this,"Please Enter All details",Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(LoginAuth.this,"Please enter Correct Password",Toast.LENGTH_LONG).show();
            }
        });

     

    }

     private void ConfirmToSubmit (){
         androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(LoginAuth.this);
         builder.setMessage("Are you want to submit the details\n For Next Step Must Upload The Profile Photo Of The User");
         builder.setCancelable(true);
         builder.setPositiveButton("Confirm", (dialog, id) -> {
             
             creationAuth.createUserWithEmailAndPassword(createUserEmailId.getText().toString(),createUserPassword.getText().toString())
                     .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                             if(task.isSuccessful()){

                                 String UserUID= Objects.requireNonNull(creationAuth.getCurrentUser()).getUid();
                                 DocumentReference creationDRFS = creationFS.collection("Users").document(UserUID);
                                 Map<String,Object> userDetails =new HashMap<>();
                                 userDetails.put("UserID",UserUID);
                                 userDetails.put("Name",createUserName.getText().toString());
                                 userDetails.put("Email",createUserEmailId.getText().toString());
                                 userDetails.put("PhoneNumber",createUserPhoneNumber.getText().toString());
                                 userDetails.put("Password",createUserPassword.getText().toString());
                                 userDetails.put("Address",createUserAddress.getText().toString());
                                 userDetails.put("Qualification",createUserQualification.getText().toString());
                                 userDetails.put("Gender",createUserGender.getSelectedItem().toString());
                                 userDetails.put("WorkZone",createUserWorkZone.getSelectedItem().toString());
                                 userDetails.put("WorkPosition",createUserWorkPosition.getSelectedItem().toString());
                                 userDetails.put("WorkUnderTeritory",createUserWorkUnderTeritory.getSelectedItem().toString());
                                 userDetails.put("UserAccessLevel",createUserWorkPosition.getSelectedItemId());

                                 creationDRFS.set(userDetails,SetOptions.merge())
                                         .addOnSuccessListener(new OnSuccessListener<Void>() {
                                             @Override
                                             public void onSuccess(Void unused) {
                                                 Toast.makeText(LoginAuth.this,"Please Wait! We Processing Your Request",Toast.LENGTH_LONG).show();
                                                 Intent intent=new Intent(LoginAuth.this,ProfileImageUpload.class);
                                                 intent.putExtra("UserID",UserUID);
                                                 intent.putExtra("Name",createUserName.getText().toString());
                                                 intent.putExtra("Email",createUserEmailId.getText().toString());
                                                 intent.putExtra("PhoneNumber",createUserPhoneNumber.getText().toString());
                                                 intent.putExtra("Password",createUserPassword.getText().toString());
                                                 intent.putExtra("Address",createUserAddress.getText().toString());
                                                 intent.putExtra("Qualification",createUserQualification.getText().toString());
                                                 intent.putExtra("Gender",createUserGender.getSelectedItem().toString());
                                                 intent.putExtra("WorkZone",createUserWorkZone.getSelectedItem().toString());
                                                 intent.putExtra("WorkPosition",createUserWorkPosition.getSelectedItem().toString());
                                                 intent.putExtra("WorkUnderTeritory",createUserWorkUnderTeritory.getSelectedItem().toString());
                                                 intent.putExtra("UserAccessLevel",createUserWorkPosition.getSelectedItemId());
                                                 startActivity(intent);
                                             }
                                         }).addOnFailureListener(new OnFailureListener() {
                                     @Override
                                     public void onFailure(@NonNull @NotNull Exception e) {
                                         Toast.makeText(LoginAuth.this,"Something Went Wrong! Please Try again after sometime",Toast.LENGTH_SHORT).show();
                                     }
                                 });


                             }else{
                                 Toast.makeText(LoginAuth.this,"Something Went Wrong! Please Try again Later",Toast.LENGTH_SHORT).show();
                             }
                         }
                     });
             
         });
         builder.setNegativeButton("Preview", (dialog, id) -> dialog.cancel());
         AlertDialog alert = builder.create();
         alert.show();
     }

}