package com.enginer.e_hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MaladeActivity extends AppCompatActivity {
    private ImageButton listconsult,btnListrv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malade);
        listconsult=findViewById(R.id.listconsult);
        btnListrv=findViewById(R.id.list_rvP);
        listconsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.fragment_list_consult2);
            }
        });

        btnListrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.fragment_list_rv_patient);
            }
        });


    }
}