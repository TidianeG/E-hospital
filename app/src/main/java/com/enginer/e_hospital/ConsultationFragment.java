package com.enginer.e_hospital;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsultationFragment} factory method to
 * create an instance of this fragment.
 */
public class ConsultationFragment extends Fragment {
    private Button backConsult;
    private  MedecinActivity mede;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_consultation, container, false);
        backConsult = view.findViewById(R.id.backConsult);
        backConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mede.setContentView(R.layout.activity_medecin);            }
        });

         return view;
    }
}