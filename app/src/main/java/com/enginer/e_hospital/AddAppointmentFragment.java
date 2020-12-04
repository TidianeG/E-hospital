package com.enginer.e_hospital;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddAppointmentFragment} factory method to
 * create an instance of this fragment.
 */
public class AddAppointmentFragment extends Fragment {

    private Button backAppoint;
    private LinearLayout layoutAppoint;
    private ScrollView layoutAddAppoint;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_appointment, container, false);
        backAppoint = view.findViewById(R.id.backAppoint);
        layoutAppoint = view.findViewById(R.id.containerListAppointment);
        layoutAddAppoint = view.findViewById(R.id.addNewAppointment);
        backAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //layoutAppoint.setVisibility(View.VISIBLE);
                layoutAddAppoint.setVisibility(View.INVISIBLE);

                FragmentTransaction ft = getFragmentManager().beginTransaction();

                ft.replace(R.id.containerAppointmentList, new AppointmentManagementFragment());
                ft.commit();
            }
        });
        return view;
    }
}