package com.example.notificationappmsg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class onBoardingfragment3 extends Fragment {

    FloatingActionButton btntologinonboard3;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_on_boarding3,container,false);

        btntologinonboard3=root.findViewById(R.id.btntologinonboard3);

        btntologinonboard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AdminDashborad.class);
                startActivity(intent);
            }
        });

        return root;
    }
}
