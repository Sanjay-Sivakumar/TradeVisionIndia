package com.example.notificationappmsg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class LoginTabFragment extends Fragment {

    EditText emailtabfragment,passwordtabfragment;
    Button btntabfragment;
    TextView forgotpasstab;

    FirebaseAuth authenticatetab;
    FirebaseFirestore fstoretab;
    String santab =new String("1");
    String santab1 =new String("2");
    String santab2 =new String("3");


    float u=0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.logintab_fragment,container,false);

        emailtabfragment=root.findViewById(R.id.ettabuseremail);
        passwordtabfragment=root.findViewById(R.id.ettabuserpassword);
        forgotpasstab=root.findViewById(R.id.tvtabforgotpass);
        btntabfragment=root.findViewById(R.id.btntabuserlogin);

        authenticatetab=FirebaseAuth.getInstance();
        fstoretab=FirebaseFirestore.getInstance();

        emailtabfragment.setTranslationX(300);
        passwordtabfragment.setTranslationX(300);
        btntabfragment.setTranslationX(300);
        forgotpasstab.setTranslationX(300);

        emailtabfragment.setAlpha(u);
        passwordtabfragment.setAlpha(u);
        forgotpasstab.setAlpha(u);
        btntabfragment.setAlpha(u);

        emailtabfragment.animate().translationX(0).alpha(1).setDuration(1400).setStartDelay(200).start();
        passwordtabfragment.animate().translationX(0).alpha(1).setDuration(1400).setStartDelay(200).start();
        forgotpasstab.animate().translationX(0).alpha(1).setDuration(1400).setStartDelay(200).start();
        btntabfragment.animate().translationX(0).alpha(1).setDuration(1400).setStartDelay(200).start();




        btntabfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticatetab.signInWithEmailAndPassword(emailtabfragment.getText().toString(),passwordtabfragment.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        FirebaseAuth authUser=FirebaseAuth.getInstance();
                        FirebaseUser UserAuth=authUser.getCurrentUser();

                        CheckUserAccessLevel(UserAuth.getUid());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),"Login Failed",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        forgotpasstab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),RegistrationPage.class);
                startActivity(intent);

            }
        });

        return root;
    }
    private void CheckUserAccessLevel(String uid) {
        DocumentReference df=fstoretab.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String levelCHecker=documentSnapshot.getString("UserLevel");

                if(Objects.equals(levelCHecker, santab))
                {
                   Intent intent = new Intent(getActivity(),ServiceEngineerDashboard.class);
                    startActivity(intent);
                }
               else if(Objects.equals(levelCHecker, santab1)){
                    Intent intent = new Intent(getActivity(), BusinessExecutiveDashboard.class);
                    startActivity(intent);
                }else
                {
                    Intent intent = new Intent(getActivity(), TeritoryManagerDashboard.class);
                    startActivity(intent);
                }

            }
        });
    }

}
