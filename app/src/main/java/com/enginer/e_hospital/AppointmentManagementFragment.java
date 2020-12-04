package com.enginer.e_hospital;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enginer.e_hospital.ui.home.HomeViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentManagementFragment} factory method to
 * create an instance of this fragment.
 */
public class AppointmentManagementFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button btnNewAppointment;
    private LinearLayout layout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointment_management, container, false);
        btnNewAppointment = view.findViewById(R.id.btnNewAppointment);
        layout = view.findViewById(R.id.containerListAppointment);

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        btnNewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.INVISIBLE);
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                ft.replace(R.id.containerAppointmentList, new AddAppointmentFragment());
                ft.commit();
            }
        });


        return view;
    }
}