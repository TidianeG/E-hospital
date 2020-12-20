package com.enginer.e_hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MedecinActivity extends AppCompatActivity {

        private ImageButton newConsult, listConsult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecin);
        newConsult = findViewById(R.id.new_consultation);
        listConsult = findViewById(R.id.list_consultation);

        newConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.fragment_consultation);

            }
        });

    }
}