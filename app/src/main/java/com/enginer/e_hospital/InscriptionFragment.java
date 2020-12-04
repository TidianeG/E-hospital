package com.enginer.e_hospital;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.enginer.e_hospital.ui.home.HomeViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InscriptionFragment} factory method to
 * create an instance of this fragment.
 */
public class InscriptionFragment extends Fragment {
    private Button backPatient;
    private ScrollView layoutAddPatient;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_inscription, container, false);
        backPatient = root.findViewById(R.id.backPatient);
        layoutAddPatient = root.findViewById(R.id.nav_inscription);
        backPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutAddPatient.setVisibility(View.INVISIBLE);
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                ft.replace(R.id.containerPatientList, new PatientManagementFragment());
                ft.commit();
            }
        });
        return root;
    }
}