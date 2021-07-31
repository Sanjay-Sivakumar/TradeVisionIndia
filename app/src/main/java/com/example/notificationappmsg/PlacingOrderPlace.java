package com.example.notificationappmsg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlacingOrderPlace extends AppCompatActivity {

    LinearLayout layoutList;
    Button buttonAdd;
    Button buttonSubmitList,Addspinnerelements,RemoveSpinnerelements;
    int count=0;
    EditText spinnerElement;

    public String RequestCodeEquip;

    List<String> teamList = new ArrayList<>();
    ArrayList<Cricketer> cricketersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placing_order_place);
        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        buttonSubmitList = findViewById(R.id.button_submit_list);
        Addspinnerelements=findViewById(R.id.addspinelement);
        RemoveSpinnerelements=findViewById(R.id.removespinelement);
        spinnerElement=findViewById(R.id.addspinnerelements);


        RequestCodeEquip= getIntent().getStringExtra("RequestCode");

        teamList.add("Model");
        teamList.add("Jaw");
        teamList.add("Cone");
        teamList.add("Fine Cone");
        teamList.add("Sander");
        teamList.add("VSI");
        teamList.add("Hydro Cyclo");

        Addspinnerelements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(spinnerElement.getText().toString()))
                {
                    teamList.add(spinnerElement.getText().toString());
                    Toast.makeText(PlacingOrderPlace.this,"Element is Added",Toast.LENGTH_LONG).show();
                }
            }
        });

        RemoveSpinnerelements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(spinnerElement.getText().toString()))
                {
                    teamList.remove(spinnerElement.getText().toString());
                    Toast.makeText(PlacingOrderPlace.this,"Element is Removed",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_add: {

                addView();
            }
                break;

            case R.id.button_submit_list:

                if(checkIfValidAndRead()){
                    if(RequestCodeEquip.equals("1"))
                    {
                        Intent intent = new Intent(PlacingOrderPlace.this, TakingOrder.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("list", cricketersList);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(RequestCodeEquip.equals("2")){
                        Intent intent = new Intent(PlacingOrderPlace.this, ServiceEntryDetails.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("list", cricketersList);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(PlacingOrderPlace.this, VisitDayDetails.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("list", cricketersList);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }

                break;

        }


    }

    private boolean checkIfValidAndRead() {
        cricketersList.clear();
        boolean result = true;

        for(int i=0;i<layoutList.getChildCount();i++){

            View cricketerView = layoutList.getChildAt(i);

            EditText editTextName = (EditText)cricketerView.findViewById(R.id.edit_cricketer_name);
            AppCompatSpinner spinnerTeam = (AppCompatSpinner)cricketerView.findViewById(R.id.spinner_team);

            Cricketer cricketer = new Cricketer();

            if(!editTextName.getText().toString().equals("")){
                cricketer.setModelNumber(editTextName.getText().toString());
            }else {
                result = false;
                break;
            }

            if(spinnerTeam.getSelectedItemPosition()!=0){
                cricketer.setModelName(teamList.get(spinnerTeam.getSelectedItemPosition()));
            }else {
                result = false;
                break;
            }

            cricketersList.add(cricketer);

        }

        if(cricketersList.size()==0){
            result = false;
            Toast.makeText(this, "Add Model First!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }


        return result;
    }

    private void addView() {

        final View cricketerView = getLayoutInflater().inflate(R.layout.row_add_cricketer,null,false);

        EditText editText = (EditText)cricketerView.findViewById(R.id.edit_cricketer_name);
        AppCompatSpinner spinnerTeam = (AppCompatSpinner)cricketerView.findViewById(R.id.spinner_team);
        ImageView imageClose = (ImageView)cricketerView.findViewById(R.id.image_remove);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,teamList);
        spinnerTeam.setAdapter(arrayAdapter);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(cricketerView);
            }
        });

        layoutList.addView(cricketerView);

    }

    private void removeView(View view){

        layoutList.removeView(view);

    }
}